(ns client.router
  (:require 
    [client.auth.subs     :as auth-subs]
    [re-frame.core        :as rf]
    [reitit.frontend      :as rfe]
    [reitit.frontend.easy :as rfee]))

;; (ns app.router
;;   (:require 
;;     [bidi.bidi     :as bidi]
;;     [pushy.core    :as pushy]
;;     [re-frame.core :as rf]))

;; (def routes 
;;   ["/" {""              :recipes
;;         "become-a-chef" :become-a-chef
;;         "saved/"        :saved
;;         "recipes/"      {"" :recipes
;;                          [:recipe-id] :recipe}
;;         "inbox/"        {"" :inboxes
;;                          [:inbox-id] :inbox}
;;         "profile"       :profile
;;         "sign-up"       :sign-up
;;         "log-in"        :log-in}])

;; (def history
;;   (let [dispatch #(rf/dispatch [:route-changed %])
;;         match    #(bidi/match-route routes %)]
;;     (pushy/pushy dispatch match)))

;; (defn start! []
;;   (pushy/start! history))

;; (defn path-for [route]
;;   (bidi/path-for routes route))

;; (defn set-token! [token]
;;   (pushy/set-token! history token))


(def routes
  (let [logged-in? @(rf/subscribe [::auth-subs/logged-in?])]
    [["/"       (if logged-in? 
                  :classes
                  :landing)]
     ["classes" :classes]
     ["demo"    :demo]
     ["landing" :landing]
     ["lessons" :lessons]
     ["log-in"  :log-in]
     ["profile" :profile]
     ["sign-up" :sign-up]]))
      
;; (def history
;;   (let [dispatch #(rf/dispatch [:route-changed %])       
;;         match    #()]))

;; (defn start! []
;;   (rfe))

