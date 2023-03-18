(ns frontend.nav.views
  (:require
    [frontend.nav.events :as events]
    [frontend.nav.subs   :as subs]
    [re-frame.core       :as rf]))


(defn nav-item [{:keys [id href name dispatch active-nav]}]
  [:li 
   [:a {:key      id
        :href     href
        :on-click dispatch}
    name]])

(defn public []
  (let [active-nav @(rf/subscribe [::subs/active-nav])
        nav-items  [{:id       :demo
                     :name     "Demo"
                     :href     "#demo"
                     :dispatch #(rf/dispatch [::events/set-active-nav :demo])}
                    {:id       :log-in
                     :name     "Log In"
                     :href     "#log-in"
                     :dispatch #(rf/dispatch [::events/set-active-nav :log-in])}
                    {:id       :sign-up
                     :name     "Sign Up"
                     :href     "#sign-up"
                     :dispatch #(rf/dispatch [::events/set-active-nav :sign-up])}]]
    [:div {:class "navbar bg-base-100"}
     [:div {:class "flex-1"}
      [:a {:class    "btn btn-ghost normal-case text-xl"
           :id       :landing
           :name     "Landing Page"
           :href     "#landing-page"
           :dispatch #(rf/dispatch [::events/set-active-nav :landing])}
       "Lesson Planner"]]
     [:div {:class "flex-none"}
      [:ul {:class "menu menu-horizontal px-1"}
       (for [{:keys [id name href dispatch]} nav-items]
         ^{:key id}
         [nav-item {:id         id 
                    :name       name
                    :href       href
                    :dispatch   dispatch
                    :active-nav active-nav}])]]]))

(defn authenticated []
  (let [active-nav @(rf/subscribe [::subs/active-nav])
        nav-items  [{:id       :classes
                     :name     "Classes"
                     :href     "#classes"
                     :dispatch #(rf/dispatch [::events/set-active-nav :classes])}
                    {:id       :lessons
                     :name     "Lessons"
                     :href     "#lessons"
                     :dispatch #(rf/dispatch [::events/set-active-nav :lessons])}]]
    [:div {:class "navbar bg-base-100"}
     [:div {:class "flex-1"}
      [:a {:class    "btn btn-ghost normal-case text-xl"
           :id       :classes
           :name     "Classes"
           :href     "#classes"
           :dispatch #(rf/dispatch [::events/set-active-nav :classes])}
       "Lesson Planner"]]
     [:div {:class "flex-none"}
      [:ul {:class "menu menu-horizontal px-1"}
       (for [{:keys [id name href dispatch]} nav-items]
         ^{:key id}
         [nav-item {:id         id 
                    :name       name
                    :href       href
                    :dispatch   dispatch
                    :active-nav active-nav}])]]]))

(defn nav []
  (let [user true]
    (if user 
      [authenticated]
      [public])))
