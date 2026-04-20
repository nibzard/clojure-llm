(defn snake_to_camel
  "	Write a function to convert a snake case string to camel case string."
  [word]
  (let [s (or word "")]
    (if (empty? s)
      s
      (apply str
             (first s)
             (map clojure.string/capitalize
                  (remove empty? (rest (clojure.string/split s #"_"))))))))