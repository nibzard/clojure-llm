(defn count_Pairs
  "Write a cljthon function which takes a list of integers and counts the number of possible unordered pairs where both elements are unequal."
  [arr n]
  (let [freqs (vals (frequencies arr))
        total-pairs (quot (* n (dec n)) 2)
        equal-pairs (reduce + (map #(quot (* % (dec %)) 2) freqs))]
    (- total-pairs equal-pairs)))