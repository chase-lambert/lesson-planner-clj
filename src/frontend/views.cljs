(ns frontend.views
  (:require
    [frontend.events :as events]
    [frontend.subs   :as subs]
    [re-frame.core   :as rf]))


(defn greeting []
  (let [name (rf/subscribe [::subs/name])]
    [:h1.text-3xl.font-extrabold.mt-6
     (str "Hello " @name)]))

(defn log-in []
  [:div "Log In"])

(defn sign-up []
  [:div "Sign Up"])

(defn demo []
  [:div "Demo"])
