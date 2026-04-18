(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [v (vec (take n (or arr [])))]
    (reduce
      (fn [acc i]
        (reduce
          (fn [acc2 j]
            (+ acc2 (bit-xor (v i) (v j))))
          acc
          (range i n)))
      0
      (range n))))