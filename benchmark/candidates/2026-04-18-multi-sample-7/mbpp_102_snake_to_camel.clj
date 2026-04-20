(defn snake_to_camel
  "	Write a function to convert a snake case string to camel case string."
  [word]
  (let [s (or word "")]
    (if (empty? s)
      s
      (let [[head & tail] (clojure.string/split s #"_+")]
        (apply str
               head
               (map clojure.string/capitalize tail))))))