(defn snake_to_camel
  "Write a function to convert a snake case string to camel case string."
  [word]
  (when word
    (let [[first-word & rest-words] (clojure.string/split word #"_")]
      (clojure.string/join (cons first-word (map clojure.string/capitalize rest-words))))))