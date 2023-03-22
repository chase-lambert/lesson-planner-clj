(ns client.components.page-nav)

(defn page-nav [{:keys [left center right]}]
  [:div {:class "navbar bg-base-100"}
   [:div {:class "navbar-start"}
    (when left [:div])]
   [:div {:class "navbar-center"}
    [:h2 {:class "font-extrabold text-3xl"}
     center]]
   [:div {:class "navbar-end"}
    (when right [:div])]])
