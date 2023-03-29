(ns client.nav.views
  (:require
    [client.auth.subs  :as auth-subs]
    [client.nav.events :as events]
    [client.nav.subs   :as subs]
    [re-frame.core     :as rf]))


(defn nav-item [{:keys [id href name on-click active-nav]}]
  [:li 
   [:a {:key      id
        :href     href
        :on-click on-click
        :class    (when (= id active-nav) "underline decoration-2 underline-offset-8")}
    name]])

(defn public []
  (let [active-nav @(rf/subscribe [::subs/active-nav])
        nav-items  [{:id       :demo
                     :name     "Demo"
                     :href     "#demo"
                     :on-click #(rf/dispatch [::events/set-active-nav :demo])}
                    {:id       :log-in
                     :name     "Log In"
                     :href     "#log-in"
                     :on-click #(rf/dispatch [::events/set-active-nav :log-in])}
                    {:id       :sign-up
                     :name     "Sign Up"
                     :href     "#sign-up"
                     :on-click #(rf/dispatch [::events/set-active-nav :sign-up])}]]
    [:div {:class "navbar bg-base-100"}
     [:div {:class "flex-1"}
      [:a {:class    "btn btn-ghost normal-case text-xl"
           :id       :landing
           :name     "Landing Page"
           :href     "#landing-page"
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
                    :active-nav active-nav}])]]]))

(defn authenticated []
  (let [active-nav @(rf/subscribe [::subs/active-nav])
        nav-items  [{:id       :classes
                     :name     "Classes"
                     :href     "#classes"
                     :on-click #(rf/dispatch [::events/set-active-nav :classes])}
                    {:id       :lessons
                     :name     "Lessons"
                     :href     "#lessons"
                     :on-click #(rf/dispatch [::events/set-active-nav :lessons])}
                    {:id       :profile
                     :name     "Profile"
                     :href     "#profile"
                     :on-click #(rf/dispatch [::events/set-active-nav :profile])}]]
    [:nav {:class "navbar bg-base-100"}
     [:div {:class "flex-1"}
      [:a {:class    "btn btn-ghost normal-case text-xl"
           :id       :classes
           :name     "Classes"
           :href     "#classes"
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
                    :active-nav active-nav}])]]]))

(defn nav []
  (let [logged-in? @(rf/subscribe [::auth-subs/logged-in?])]
    (if logged-in? 
      [authenticated]
      [public])))
