(ns frontend.core
  (:require 
    [frontend.events :as events]
    [frontend.views :refer [log-in sign-up]]

    ;; -- classes -- 
    [frontend.sections.classes.view.classes :refer [classes]]

    ;; -- demo -- 
    [frontend.sections.demo.view.demo :refer [demo]]

    ;; -- landing -- 
    [frontend.sections.landing.view.landing :refer [landing]]

    ;; -- lessons -- 
    [frontend.sections.lessons.view.lessons :refer [lessons]]

    ;; -- nav --
    [frontend.nav.events]
    [frontend.nav.subs :as subs]
    [frontend.nav.views.nav :refer [nav]]

    [reagent.dom :as rdom]
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
  (rf/dispatch-sync [::events/initialize-db])
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (start))
