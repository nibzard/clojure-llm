(defn snake_to_camel
  "	Write a function to convert the given snake case string to camel case string."
  [word]
  (when (some? word)
    (let [s (or word "")]
      (apply str
             (first s)
             (map-indexed
              (fn [i ch]
                (if (= \_ (nth s i 0))
                  (Character/toUpperCase ^char (nth s (inc i)))
                  (Character/toLowerCase ^char ch)))
              (rest s))))))