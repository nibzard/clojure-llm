(defn odd_length_sum
  "	Write a cljthon function to find the sum of all odd length subvectors. https://www.geeksforgeeks.org/sum-of-all-odd-length-subvectors/"
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (reduce-kv
      (fn [acc i x]
        (let [total-subarrays (* (inc i) (- n i))
              odd-count (quot (inc total-subarrays) 2)]
          (+ acc (* x odd-count))))
      0
      v)))