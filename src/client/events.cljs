(ns client.events
  (:require 
    [client.auth.events :as auth-events]
    [client.db     :refer [initial-app-db]]
    [re-frame.core :as rf]))


(rf/reg-event-fx
  ::initialize-db
  [(rf/inject-cofx ::auth-events/local-store-user)]
  (fn [{:keys [::auth-events/local-store-user]} _]
    {:db (assoc-in initial-app-db [:auth] local-store-user)}))

