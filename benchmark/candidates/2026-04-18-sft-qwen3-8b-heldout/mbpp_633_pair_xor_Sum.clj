(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take n (or arr []))]
    (reduce
      (fn [acc x]
        (reduce
          (fn [acc y]
            (+ acc (bit-xor x y)))
          acc
          xs))
      0
      xs)))