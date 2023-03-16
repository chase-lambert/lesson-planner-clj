(ns frontend.events
  (:require 
    [re-frame.core :as rf]
    [frontend.db :refer [initial-app-db]]))


(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    initial-app-db))

(rf/reg-event-db
  ::set-active-page
  (fn [db [_ active-page]]
    (assoc-in db [:nav :active-page] active-page)))
