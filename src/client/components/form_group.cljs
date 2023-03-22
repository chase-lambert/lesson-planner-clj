(ns client.components.form-group)

(defn form-group [{:keys [id label type values]}]
  [:div {:class "form-control"}
   [:label {:class "input-group input-group-vertical"}
    [:span label]
    [:input {:type type
             :id id
             :value (id @values)
             :on-change #(swap! values assoc id (.. % -target -value))}]]])
             
              
