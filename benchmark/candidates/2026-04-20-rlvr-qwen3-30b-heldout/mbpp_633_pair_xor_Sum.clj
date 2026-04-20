(defn pair_xor_Sum
  "	Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [xs (take n (or arr []))]
    (->> xs
         (map-indexed (fn [i x]
                        (reduce
                         (fn [acc j]
                           (+ acc (bit-xor x (nth xs j))))
                         0
                         (range (inc i) (count xs))))
                      xs)
         (reduce + 0))))