(defn snake_to_camel
  "	Write a function to convert the given snake case string to camel case string."
  [word]
  (when word
    (let [s (or word "")]
      (clojure.string/capitalize
       (apply str
              (map-indexed
               (fn [idx ch]
                 (if (= ch \_)
                   (Character/toUpperCase (nth s (inc idx)))
                   ch))
               s))))))