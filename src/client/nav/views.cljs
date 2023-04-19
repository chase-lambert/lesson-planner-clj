(ns client.nav.views
  (:require
    [client.auth.subs  :as auth-subs]
    [client.nav.events :as events]
    [client.nav.subs   :as subs]
    [client.router     :as router]
    [re-frame.core     :as rf]))


(defn nav-item [{:keys [id href name on-click active-page]}]
  [:li 
   [:a {:key      id
        :href     href
        :on-click on-click
        :class    (when (= id active-page) "underline decoration-2 underline-offset-8")}
    name]])

(defn public []
  (let [active-page @(rf/subscribe [::subs/active-page])
        nav-items  [{:id       :demo
                     :name     "Demo"
                     :href     (router/path-for :demo)
                     :on-click #(rf/dispatch [::events/set-active-nav :demo])}
                    {:id       :sign-up
                     :name     "Sign Up"
                     :href     (router/path-for :sign-up)
                     :on-click #(rf/dispatch [::events/set-active-nav :sign-up])}
                    {:id       :log-in
                     :name     "Log In"
                     :href     (router/path-for :log-in)
                     :on-click #(rf/dispatch [::events/set-active-nav :log-in])}]]
    [:div {:class "navbar bg-base-100"}
     [:div {:class "flex-1"}
      [:a {:class    "btn btn-ghost normal-case text-xl"
           :id       :landing
           :name     "Landing Page"
           :href     (router/path-for :landing)
           :on-click #(rf/dispatch [::events/set-active-nav :landing])}
       "Lesson Planner"]]
     [:div {:class "flex-none"}
      [:ul {:class "menu menu-horizontal px-1"}
       (for [{:keys [id name href on-click]} nav-items]
         ^{:key id}
         [nav-item {:id         id 
                    :name       name
                    :href       href
                    :on-click   on-click
                    :active-page active-page}])]]]))

(defn authenticated []
  (let [active-page @(rf/subscribe [::subs/active-page])
        nav-items  [{:id       :classes
                     :name     "Classes"
                     :href     (router/path-for :classes)
                     :on-click #(rf/dispatch [::events/set-active-nav :classes])}
                    {:id       :lessons
                     :name     "Lessons"
                     :href     (router/path-for :lessons)
                     :on-click #(rf/dispatch [::events/set-active-nav :lessons])}
                    {:id       :profile
                     :name     "Profile"
                     :href     (router/path-for :profile)
                     :on-click #(rf/dispatch [::events/set-active-nav :profile])}]]
    [:nav {:class "navbar bg-base-100"}
     [:div {:class "flex-1"}
      [:a {:class    "btn btn-ghost normal-case text-xl"
           :id       :classes
           :name     "Classes"
           :href     (router/path-for :classes)
           :on-click #(rf/dispatch [::events/set-active-nav :classes])}
       "Lesson Planner"]]
     [:div {:class "flex-none"}
      [:ul {:class "menu menu-horizontal px-1"}
       (for [{:keys [id name href on-click]} nav-items]
         ^{:key id}
         [nav-item {:id         id 
                    :name       name
                    :href       href
                    :on-click   on-click
                    :active-page active-page}])]]]))

(defn nav []
  (let [logged-in? @(rf/subscribe [::auth-subs/logged-in?])]
    (if logged-in? 
      [authenticated]
      [public])))
