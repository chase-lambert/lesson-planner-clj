(ns frontend.nav.views.nav
  (:require 
    [frontend.nav.views.authenticated :refer [authenticated]]
    [frontend.nav.views.public        :refer [public]]))

(defn nav []
  (let [user false]
    (if user 
      [authenticated]
      [public])))
