(ns frontend.nav.views.public
  (:require
    [frontend.nav.views.nav-item :refer [nav-item]]
    [frontend.nav.events :as events]
    [frontend.nav.subs   :as subs]
    [re-frame.core   :as rf]))

(defn public []
  (let [active-nav @(rf/subscribe [::subs/active-nav])
        nav-items  [{:id :demo
                     :name "Demo"
                     :href "#demo"
                     :dispatch #(rf/dispatch [::events/set-active-nav :demo])}
                     ;; {:id :landing
                     ;;  :name "Landing Page"
                     ;;  :href "#landing-page"
                     ;;  :dispatch #(rf/dispatch [::events/set-active-page :landing])}
                    {:id :log-in
                     :name "Log In"
                     :href "#log-in"
                     :dispatch #(rf/dispatch [::events/set-active-nav :log-in])}
                    {:id :sign-up
                     :name "Sign Up"
                     :href "#sign-up"
                     :dispatch #(rf/dispatch [::events/set-active-nav :sign-up])}]]
    [:div.navbar.bg-base-100
     [:div.flex-1
      [:a.btn.btn-ghost.normal-case.text-xl
       "Lesson Planner"]]
     [:div.flex-none
      [:ul.menu.menu-horizontal.px-1
       (for [{:keys [id name href dispatch]} nav-items]
         ^{:key id}
         [nav-item {:id id 
                    :name name
                    :href href
                    :dispatch dispatch
                    :active-nav active-nav}])]]]))
