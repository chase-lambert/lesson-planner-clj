(ns client.auth.views
  (:require 
    [client.components.form-group :refer [form-group]]
    [client.components.page-nav :refer [page-nav]]
    [reagent.core :as r]
    [re-frame.core :as rf]))

(defn sign-up []
  (let [initial-values {:first-name ""
                        :last-name ""
                        :email ""
                        :password ""}
        values (r/atom initial-values)]
    (fn []
      [:div {:class "flex flex-col"}
       [page-nav {:center "Sign up"}]
       [form-group {:id :first-name
                    :label "First Name"
                    :type "text"
                    :values values}]
       [form-group {:id :last-name
                    :label "Last Name"
                    :type "text"
                    :values values}]
       [form-group {:id :email
                    :label "Email"
                    :type "email"
                    :values values}]
       [form-group {:id :password
                    :label "Password"
                    :type "password"
                    :values values}]])))

(defn log-in [])
(defn profile [])
