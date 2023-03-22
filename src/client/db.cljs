(ns client.db)


(def initial-app-db 
  {:auth   {:uid nil}

   :errors {}

   :nav    {:active-page :landing
            :active-nav  :landing}

   :users  {}})

