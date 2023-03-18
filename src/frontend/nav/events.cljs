(ns frontend.nav.events
  (:require 
    [re-frame.core :as rf]
    [frontend.db :refer [initial-app-db]]))

(rf/reg-event-db
  ::set-active-nav
  (fn [db [_ active-nav]]
    (assoc-in db [:nav :active-nav] active-nav)))
