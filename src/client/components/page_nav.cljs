(ns client.components.page-nav)

(defn page-nav [{:keys [left center right]}]
  [:div {:class ""}
   [:div {:class ""}
    (when left left)]
   [:div {:class ""}
    [:h2 {:class ""}
     center]]
   [:div {:class ""}
    (when right right)]])
