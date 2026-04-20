(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take n (or arr []))]
    (reduce
      +
      (for [x xs
            y xs]
        (bit-xor x y)))))