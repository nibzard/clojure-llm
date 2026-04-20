(defn odd_length_sum
  "Write a cljthon function to find the sum of all odd length subvectors. https://www.geeksforgeeks.org/sum-of-all-odd-length-subvectors/"
  [arr]
  (let [n (count arr)]
    (reduce-kv
      (fn [total idx x]
        (+ total (* x (quot (* (inc idx) (- n idx)) 2))))
      0
      (vec arr))))