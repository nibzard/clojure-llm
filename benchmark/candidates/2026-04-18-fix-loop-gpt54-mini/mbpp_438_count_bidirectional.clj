(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies test_list)]
    (reduce
      (fn [acc [a b :as v]]
        (if (= a b)
          acc
          (let [rv [b a]]
            (if (and (contains? freqs rv) (neg? (compare a b)))
              (+ acc (* (get freqs v 0) (get freqs rv 0)))
              acc))))
      0
      (keys freqs))))