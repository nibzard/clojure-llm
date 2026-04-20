(defn pair_xor_Sum
  "Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take (or n 0) (or arr []))
        cnt (count xs)]
    (if (< cnt 2)
      0
      (reduce
       +
       (for [i (range cnt)
             j (range (inc i) cnt)]
         (bit-xor (nth xs i) (nth xs j)))))))