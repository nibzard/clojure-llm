(defn snake_to_camel
  "	Write a function to convert a snake case string to camel case string."
  [word]
  (if (nil? word)
    nil
    (let [parts (clojure.string/split word #"_")]
      (apply str
             (first parts)
             (map clojure.string/capitalize (rest parts))))))