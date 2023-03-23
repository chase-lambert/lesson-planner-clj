(ns client.sections.demo.views
  (:require
    [client.components.page-nav :refer [page-nav]])) 
    
(defn demo []
  [page-nav {:center "Demo"}])

