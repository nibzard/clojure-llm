(defn count_Pairs
  "Write a cljthon function which takes a list of integers and counts the number of possible unordered pairs where both elements are unequal."
  [arr n]
  (let [freqs (frequencies (take n arr))
        total-pairs (if (and n (>= n 2))
                      (quot (* n (dec n)) 2)
                      0)
        equal-pairs (reduce + (map (fn [[_ c]] (quot (* c (dec c)) 2)) freqs))]
    (- total-pairs equal-pairs)))