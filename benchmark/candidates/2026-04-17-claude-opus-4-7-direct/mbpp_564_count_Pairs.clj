(defn count-pairs
  "Counts the number of possible unordered pairs where both elements are unequal."
  [arr n]
  (let [freq (frequencies arr)
        total-pairs (quot (* n (dec n)) 2)
        equal-pairs (reduce (fn [acc [k v]]
                              (+ acc (quot (* v (dec v)) 2)))
                            0
                            freq)]
    (- total-pairs equal-pairs)))