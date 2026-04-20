(defn snake_to_camel
  "Write a function to convert a snake case string to camel case string."
  [word]
  (when word
    (let [parts (clojure.string/split word #"_")]
      (if (empty? parts)
        ""
        (clojure.string/join (map clojure.string/capitalize parts))))))