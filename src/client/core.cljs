(ns client.core
  (:require
   [client.auth.events]
   [client.auth.subs  :as auth-subs]
   [client.auth.views :refer [log-in profile sign-up]] 
   [client.events]
   [client.nav.events]
   [client.nav.subs]
   [client.nav.views :refer [nav]]
   [client.sections.classes.views :refer [classes]]
   [client.sections.demo.views    :refer [demo]]
   [client.sections.landing.views :refer [landing]]
   [client.sections.lessons.views :refer [lessons]]
   [clojure.string  :as string]
   [re-frame.core   :as rf]
   [reagent.dom     :as rdom]
   [shadow.resource :as rc]))


;; The tailwind watch compiler does not work well with clojurescript's
;; hot code reloading so I am bringing in tailwind's cdn for dev use
;; but do not want it included in production builds. 
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
  (let [logged-in? @(rf/subscribe [::auth-subs/logged-in?])]
    (case page-name
      :classes  [classes]
      :demo     [demo]
      :lessons  [lessons]
      :log-in   [log-in]
      :profile  [profile]
      :sign-up  [sign-up]
      (if logged-in? 
        [classes]
        [landing]))))

(defn app []
  (let [active-nav @(rf/subscribe [:client.nav.subs/active-nav])]
    [:div.container
     (when TW         ;; Only include tailwind cdn for dev use
      [tailwindcdn])
     [nav]
     [pages active-nav]]))

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (rf/dispatch-sync [:client.events/initialize-db])
  (start))
