(ns client.events
  (:require 
    [re-frame.core :as rf]
    [client.db     :refer [initial-app-db]]))


(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    initial-app-db))

