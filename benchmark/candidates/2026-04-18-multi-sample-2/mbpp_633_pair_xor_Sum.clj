(defn pair_xor_Sum
  "Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take (or n 0) (or arr []))
        bit-limit (reduce (fn [m x] (max m (if (nil? x) 0 (Integer/numberOfLeadingZeros 0)))) 0 [])]
    (if (or (nil? arr) (<= (count xs) 1))
      0
      (let [max-val (reduce max 0 xs)
            bits (if (zero? max-val)
                   1
                   (- 32 (Integer/numberOfLeadingZeros (int max-val))))]
        (reduce
         +
         (for [b (range bits)]
           (let [ones (count (filter #(not (zero? (bit-and % (bit-shift-left 1 b)))) xs))
                 zeros (- (count xs) ones)]
             (* ones zeros (bit-shift-left 1 b))))))))