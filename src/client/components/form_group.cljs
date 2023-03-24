(ns client.components.form-group)

(defn form-group [{:keys [id label type values]}]
  [:div {:class ""}
   [:label {:class ""}
    [:span label]
    [:input {:type  type
             :id    id
             :value (id @values)
             :on-change #(swap! values assoc id (.. % -target -value))}]]])
             
              
