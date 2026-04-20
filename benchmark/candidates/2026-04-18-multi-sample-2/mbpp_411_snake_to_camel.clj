(defn snake_to_camel
  "	Write a function to convert the given snake case string to camel case string."
  [word]
  (let [parts (clojure.string/split (or word "") #"_")]
    (apply str
           (first parts)
           (map clojure.string/capitalize (rest parts)))))