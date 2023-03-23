(ns client.core
  (:require
   [client.auth.events]
   [client.auth.subs]
   [client.auth.views :refer [log-in profile sign-up]] ;; -- nav --
   [client.events]
   [client.nav.events]
   [client.nav.subs]
   [client.nav.views :refer [nav]]
   [client.sections.classes.views :refer [classes]]
   [client.sections.demo.views    :refer [demo]]
   [client.sections.landing.views :refer [landing]]
   [client.sections.lessons.views :refer [lessons]] ;; -- auth --
   [clojure.string :as string]
   [re-frame.core :as rf]
   [reagent.dom   :as rdom]
   [shadow.resource :as rc]))


(goog-define TW false)

(defn tailwindcdn []
  (let [config (-> (rc/inline "tailwind.config.js")
                   (string/replace #"^module.exports" "tailwind.config")
                   (string/replace #"require\(.*\)" ""))]
    (js/console.log "tailwindcdn enabled")
    [:<> 
     [:script {:src "https://cdn.tailwindcss.com/3.2.4"}]
     [:script {:dangerouslySetInnerHTML {:__html config}}]]))
    

(defn pages [page-name]
  (case page-name
    :classes  [classes]
    :demo     [demo]
    :lessons  [lessons]
    :log-in   [log-in]
    :profile  [profile]
    :sign-up  [sign-up]
    [landing]))

(defn app []
  (let [active-nav @(rf/subscribe [:client.nav.subs/active-nav])]
    [:div.container
     (when TW
      [tailwindcdn])
     [nav]
     [pages active-nav]]))

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (rf/dispatch-sync [:client.events/initialize-db])
  (start))
