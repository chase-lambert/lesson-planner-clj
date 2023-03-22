(ns client.core
  (:require 
    [client.events]

    [client.sections.classes.views :refer [classes]]
    [client.sections.demo.views    :refer [demo]]
    [client.sections.landing.views :refer [landing]]
    [client.sections.lessons.views :refer [lessons]]

    ;; -- auth --
    [client.auth.events]
    [client.auth.subs]
    [client.auth.views :refer [log-in profile sign-up]]

    ;; -- nav --
    [client.nav.events]
    [client.nav.subs]
    [client.nav.views :refer [nav]]

    [reagent.dom   :as rdom]
    [re-frame.core :as rf]))

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
     [nav]
     [pages active-nav]]))

(defn ^:dev/after-load start []
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (rf/dispatch-sync [:client.events/initialize-db])
  (start))
