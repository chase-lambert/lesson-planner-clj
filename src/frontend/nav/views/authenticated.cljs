(ns frontend.nav.views.authenticated
  (:require
    [frontend.nav.views.nav-item :refer [nav-item]]
    [frontend.nav.events         :as events]
    [frontend.nav.subs           :as subs]
    [re-frame.core               :as rf]))

(defn authenticated []
  (let [active-nav @(rf/subscribe [::subs/active-nav])
        nav-items  [{:id :classes
                     :name "Classes"
                     :href "#classes"
                     :dispatch #(rf/dispatch [::events/set-active-nav :classes])}
                    {:id :lessons
                     :name "Lessons"
                     :href "#lessons"
                     :dispatch #(rf/dispatch [::events/set-active-nav :lessons])}]]
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
