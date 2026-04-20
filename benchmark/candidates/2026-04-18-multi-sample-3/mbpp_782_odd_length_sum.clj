(defn odd_length_sum
  "	Write a cljthon function to find the sum of all odd length subvectors. https://www.geeksforgeeks.org/sum-of-all-odd-length-subvectors/"
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (reduce-kv
      (fn [sum i x]
        (+ sum (* x (quot (+ (* (inc i) (- n i)) 1) 2))))
      0
      v)))