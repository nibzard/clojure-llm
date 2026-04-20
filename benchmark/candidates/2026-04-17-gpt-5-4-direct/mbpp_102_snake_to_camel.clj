(defn snake_to_camel
  "	Write a function to convert a snake case string to camel case string."
  [word]
  (let [s (or word "")]
    (if (empty? s)
      s
      (let [[first-part & rest-parts] (clojure.string/split s #"_+")]
        (apply str
               first-part
               (map clojure.string/capitalize rest-parts))))))