(ns frontend.nav.views.nav-item)

(defn nav-item [{:keys [id href name dispatch active-nav]}]
  [:li 
   [:a {:key id
        :href href
        :on-click dispatch}
    name]])
