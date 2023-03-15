(ns backend.query
 (:require [clj-http.client   :as client]
           [clojure.data.json :as json]))

(def api-key (System/getenv "OPEN_AI"))

(defn request [prompt max-tokens]
  (let [prompt (str "{\"prompt\": \"" prompt "\", \"max_tokens\": " max-tokens "}")]
    (client/post "https://api.openai.com/v1/engines/davinci/completions" 
                 {:headers {"content-type" "application/json", 
                            "authorization" (str "Bearer " api-key)} 
                  :body prompt})))

(defn text [response]
  (-> response :body (json/read-str :key-fn keyword) :choices first :text))

(comment
  (def sample-response (request "Once upon a time" 50))
  (str "Once upon a time" (text sample-response)) ;; "Once upon a time, con artists disguised themselves with soiled white long underwear - this brought back memories of Charles Ponzi's era. The era of \"Where's the Beef?' This era of bull market era where everybody can do it has officially run its course."
  
  
  ,)


