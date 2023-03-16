(ns frontend.core
  (:require 
    [frontend.events :as events]
    [frontend.subs :as subs]
    [frontend.views :refer [demo header landing log-in sign-up]]
    [reagent.dom :as rdom]
    [re-frame.core :as rf]))

(defn pages [page-name]
  (case page-name
    :log-in   [log-in]
    :sign-up  [sign-up]
    :demo     [demo]
    [landing]))

(defn app []
  (let [active-page @(rf/subscribe [::subs/active-page])]
    [:div.container
     [header]
     [pages active-page]]))

(defn ^:dev/after-load start []
  (rf/dispatch-sync [::events/initialize-db])
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (start))
