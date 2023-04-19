(ns client.router
  (:require 
    [bidi.bidi         :as bidi]
    [client.auth.subs  :as auth-subs]
    [pushy.core        :as pushy]
    [re-frame.core     :as rf]))
  ;;   [re-frame.core        :as rf]
  ;;   [reitit.frontend      :as rfe]
  ;;   [reitit.frontend.easy :as rfee]))

(def routes
  (let [logged-in? @(rf/subscribe [::auth-subs/logged-in?])]
    ["/" {"" (if logged-in? 
               :classes
               :landing)
          "classes" :classes
          "demo"    :demo
          "landing" :landing
          "lessons" :lessons
          "log-in"  :log-in
          "profile" :profile
          "sign-up" :sign-up}]))

(def history
  (let [dispatch #(rf/dispatch [:client.nav.events/route-changed %])
        match    #(bidi/match-route routes %)]
    (pushy/pushy dispatch match)))

(defn start! []
  (pushy/start! history))

(defn path-for [route]
  (bidi/path-for routes route))

(defn set-token! [token]
  (pushy/set-token! history token))
      
