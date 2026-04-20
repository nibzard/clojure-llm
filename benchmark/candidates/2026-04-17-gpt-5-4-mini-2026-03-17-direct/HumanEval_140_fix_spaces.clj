(defn fix_spaces
  [text]
  (when (some? text)
    (let [s (clojure.string/trim text)]
      (-> s
          (clojure.string/replace #" {3,}" "-")
          (clojure.string/replace #" " "_")))))