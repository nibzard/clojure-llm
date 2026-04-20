(defn snake_to_camel
  "Write a function to convert the given snake case string to camel case string."
  [word]
  (if (empty? word)
    word
    (let [[first-word & rest-words] (clojure.string/split word #"_")]
      (apply str first-word (map clojure.string/capitalize rest-words)))))