(ns frontend.core
  (:require 
    [frontend.events :as events]
    [frontend.views  :refer [log-in sign-up]]

    [frontend.sections.classes.views :refer [classes]]
    [frontend.sections.demo.views    :refer [demo]]
    [frontend.sections.landing.views :refer [landing]]
    [frontend.sections.lessons.views :refer [lessons]]

    ;; -- nav --
    [frontend.nav.events]
    [frontend.nav.subs  :as subs]
    [frontend.nav.views :refer [nav]]

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
