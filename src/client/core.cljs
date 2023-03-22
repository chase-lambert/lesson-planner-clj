(ns client.core
  (:require 
    [client.events :as events]
    [client.views  :refer [log-in sign-up]]

    [client.sections.classes.views :refer [classes]]
    [client.sections.demo.views    :refer [demo]]
    [client.sections.landing.views :refer [landing]]
    [client.sections.lessons.views :refer [lessons]]

    ;; -- nav --
    [client.nav.events]
    [client.nav.subs  :as subs]
    [client.nav.views :refer [nav]]

    [reagent.dom   :as rdom]
    [re-frame.core :as rf]))

(defn pages [page-name]
  (case page-name
    :log-in   [log-in]
    :sign-up  [sign-up]
    :demo     [demo]
    :classes  [classes]
    :lessons  [lessons]
    [landing]))

(defn app []
  (let [active-nav @(rf/subscribe [::subs/active-nav])]
    [:div.container
     [nav]
     [pages active-nav]]))

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (rf/dispatch-sync [::events/initialize-db])
  (start))
