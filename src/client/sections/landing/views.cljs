(ns client.sections.landing.views
  (:require
    [client.components.page-nav :refer [page-nav]])) 
    

(defn landing []
  [page-nav {:center "Landing"}])

