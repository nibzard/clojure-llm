(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (filter #(and (vector? %) (= 2 (count %))) test_list))]
    (reduce
      (fn [total [[a b] n]]
        (if (<= (compare a b) 0)
          (+ total (if (= a b)
                     (quot n 2)
                     (min n (get freqs [b a] 0))))
          total))
      0
      freqs)))