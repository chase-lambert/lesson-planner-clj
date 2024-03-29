(ns client.auth.views
  (:require 
    [client.auth.events :as auth-events]
    [client.auth.subs   :as auth-subs]
    [client.components.form-group :refer [form-group]]
    [client.components.page-nav   :refer [page-nav]]
    [client.nav.events :as nav-events]
    [client.router :as router]
    [reagent.core  :as r]
    [re-frame.core :as rf]))

(defn log-in []
  (let [initial-values {:email    ""
                        :password ""}
        values (r/atom initial-values)]
    (fn []
      [:div {:class "flex flex-col"}
       [page-nav {:center "Log In"}]
       [form-group {:id     :email
                    :label  "Email"
                    :type   "email"
                    :values values}]
       [form-group {:id     :password
                    :label  "Password"
                    :type   "password"
                    :values values}]
       [:a {:href (router/path-for :sign-up)
            :on-click #(rf/dispatch [::nav-events/set-active-page :sign-up])}
        "New to Lesson Planner? Create an account!"]
       [:div 
        [:button {:class "btn"
                  :on-click #(rf/dispatch [::auth-events/log-in @values])}
         "Log In"]]])))

(defn sign-up []
  (let [initial-values {:first-name ""
                        :last-name  ""
                        :email      ""
                        :password   ""}
        values (r/atom initial-values)]
    (fn []
      [:div {:class "flex flex-col"}
       [page-nav {:center "Sign Up"}]
       [form-group {:id     :first-name
                    :label  "First Name"
                    :type   "text"
                    :values values}]
       [form-group {:id     :last-name
                    :label  "Last Name"
                    :type   "text"
                    :values values}]
       [form-group {:id     :email
                    :label  "Email"
                    :type   "email"
                    :values values}]
       [form-group {:id     :password
                    :label  "Password"
                    :type   "password"
                    :values values}]
       [:a {:href (router/path-for :log-in)
            :on-click #(rf/dispatch [::nav-events/set-active-page :log-in])}
        "Already have an account? Log in!"]
       [:div 
        [:button {:class "btn"
                  :on-click #(rf/dispatch [::auth-events/sign-up @values])}
         "Sign Up"]]])))
      

(defn profile []
  (let [{:keys [first-name last-name]} @(rf/subscribe [::auth-subs/active-user-profile])
        initial-values {:first-name first-name
                        :last-name  last-name}
        values (r/atom initial-values)]
    (fn []
      [:div {:class "flex flex-col"}
       [page-nav {:right [:button {:class "btn"
                                   :on-click #(rf/dispatch [::auth-events/log-out])}
                          "Log Out"]
                  :center "Profile"}]
       [form-group {:id     :first-name
                    :label  "First Name"
                    :type   "text"
                    :values values}]
       [form-group {:id     :last-name
                    :label  "Last Name"
                    :type   "text"
                    :values values}]
       ;; [form-group {:id :email
       ;;              :label "Email"
       ;;              :type "email"
       ;;              :values values}]
       ;; [form-group {:id :password
       ;;              :label "Password"
       ;;              :type "password"
       ;;              :values values}]
       [:button {:class "btn"
                 :on-click #(rf/dispatch [::auth-events/update-profile @values])}
        "Save"]
       [:button {:class "btn btn-warning"
                 :on-click #(when (js/confirm "This will delete your account")
                              (rf/dispatch [::auth-events/delete-account @values]))}
        "Delete Account"]])))
