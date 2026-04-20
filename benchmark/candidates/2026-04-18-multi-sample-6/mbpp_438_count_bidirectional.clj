(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (filter #(and (vector? %) (= 2 (count %))) test_list))
        self-pairs (reduce
                    (fn [acc [[a b] n]]
                      (if (= a b)
                        (+ acc (quot n 2))
                        acc))
                    0
                    freqs)
        mutual-pairs (reduce
                      (fn [acc [[a b] n]]
                        (if (and (not= a b) (neg? (compare a b)))
                          (+ acc (min n (get freqs [b a] 0)))
                          acc))
                      0
                      freqs)]
    (+ self-pairs mutual-pairs)))