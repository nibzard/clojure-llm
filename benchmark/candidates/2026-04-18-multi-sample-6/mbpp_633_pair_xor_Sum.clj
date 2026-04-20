(defn pair_xor_Sum
  "Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (vec (take (or n 0) (or arr [])))
        cnt (count xs)]
    (if (< cnt 2)
      0
      (let [max-bits (reduce max 0 (map #(if (zero? %) 1 (.bitLength (biginteger (Math/abs (long %))))) xs))]
        (reduce
         +
         (for [b (range max-bits)]
           (let [ones  (count (filter #(not (zero? (bit-and % (bit-shift-left 1 b)))) xs))
                 zeros (- cnt ones)]
             (* ones zeros (bit-shift-left 1 b))))))))