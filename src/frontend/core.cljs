(ns frontend.core
  (:require 
    [frontend.events :as events]
    [frontend.subs :as subs]
    [frontend.views :refer [demo greeting log-in sign-up]]
    [reagent.dom :as rdom]
    [re-frame.core :as rf]))

(defn pages [page-name]
  (case page-name
    :greeting [greeting]
    :log-in  [log-in]
    :sign-up [sign-up]
    :demo [demo]
    [greeting])) 

(defn app []
  [:div.container
   [greeting]])

(defn ^:dev/after-load start []
  (rf/dispatch-sync [::events/initialize-db])
  (rdom/render [app]
    (.getElementById js/document "app")))

(defn ^:export main []
  (start))
