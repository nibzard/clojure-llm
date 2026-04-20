(defn snake_to_camel
  "	Write a function to convert the given snake case string to camel case string."
  [word]
  (if (seq word)
    (let [parts (re-seq #"[^_]+" word)]
      (apply str
             (first parts)
             (map #(clojure.string/capitalize (apply str %)) (rest parts))))
    ""))