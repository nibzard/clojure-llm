(defn snake_to_camel
  "	Write a function to convert a snake case string to camel case string."
  [word]
  (let [[first-part & rest-parts] (clojure.string/split word #"_")]
    (apply str first-part (map clojure.string/capitalize rest-parts))))