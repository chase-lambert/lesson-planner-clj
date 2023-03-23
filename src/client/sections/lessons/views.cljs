(ns client.sections.lessons.views
  (:require
    [client.components.page-nav :refer [page-nav]])) 
    
(defn lessons []
  [page-nav {:center "Lessons"}])

