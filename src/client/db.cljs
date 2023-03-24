(ns client.db)


(def initial-app-db 
  {:auth   {:uid nil}

   :errors {}

   :nav    {:active-page :landing
            :active-nav  :landing}

   :users  {"chaselambert@gmail.com" {:uid     "chaselambert@gmail.com"
                                      :profile {:first-name "Chase"
                                                :last-name  "Lambert"
                                                :email      "chaselambert@gmail.com"
                                                :password   "password"}}}})

