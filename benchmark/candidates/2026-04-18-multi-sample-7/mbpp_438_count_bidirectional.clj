(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (filter #(and (vector? %)
                                         (= 2 (count %)))
                                   test_list))]
    (reduce
     (fn [total [[a b] n]]
       (if (= a b)
         total
         (+ total (min n (get freqs [b a] 0)))))
     0
     (filter (fn [[[a b] _]]
               (neg? (compare a b)))
             freqs))))