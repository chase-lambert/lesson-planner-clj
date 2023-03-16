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

(defn landing []
  [:div "landing"])

(defn page [{:keys [id href name dispatch active-page]}]
  [:li 
   [:a {:key id
        :href href
        :on-click dispatch}
    name]])
         

(defn header []
  (let [active-page @(rf/subscribe [::subs/active-page])
        pages       [{:id :demo
                      :name "Demo"
                      :href "#demo"
                      :dispatch #(rf/dispatch [::events/set-active-page :demo])}
                     ;; {:id :landing
                     ;;  :name "Landing Page"
                     ;;  :href "#landing-page"
                     ;;  :dispatch #(rf/dispatch [::events/set-active-page :landing])}
                     {:id :log-in
                      :name "Log In"
                      :href "#log-in"
                      :dispatch #(rf/dispatch [::events/set-active-page :log-in])}
                     {:id :sign-up
                      :name "Sign Up"
                      :href "#Sign Up"
                      :dispatch #(rf/dispatch [::events/set-active-page :sign-up])}]]
    [:div.navbar.bg-base-100
     [:div.flex-1
      [:a.btn.btn-ghost.normal-case.text-xl
       "Lesson Planner"]]
     [:div.flex-none
      [:ul.menu.menu-horizontal.px-1
       (for [{:keys [id name href dispatch]} pages]
         ^{:key id}
         [page {:id id 
                :name name
                :href href
                :dispatch dispatch
                :active-page active-page}])]]]))
