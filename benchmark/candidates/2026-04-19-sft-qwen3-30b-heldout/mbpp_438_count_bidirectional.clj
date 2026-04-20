(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce-kv (fn [acc k v]
                 (if (and (vector? k)
                          (= (count k) 2)
                          (contains? freqs (vec (reverse k))))
                   (+ acc (min v (get freqs (vec (reverse k)) 0)))
                   acc))
               0
               freqs)))