(ns frontend.subs
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub
  ::name 
  (fn [db _]
    (:name db)))

(rf/reg-sub
  ::active-page
  (fn [db _]
    (get-in db [:nav :active-page])))
