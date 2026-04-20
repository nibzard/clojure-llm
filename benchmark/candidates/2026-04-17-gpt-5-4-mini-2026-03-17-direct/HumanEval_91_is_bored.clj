(defn is_bored
  [S]
  (let [sentences (-> (or S "")
                      (clojure.string/replace #"[\.\?!]+" "|")
                      (clojure.string/split #"\|"))]
    (count
     (filter #(re-find #"^\s*I\b" %) sentences))))