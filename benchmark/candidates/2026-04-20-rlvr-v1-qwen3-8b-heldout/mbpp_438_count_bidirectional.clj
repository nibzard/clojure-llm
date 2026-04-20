(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies test_list)]
    (reduce
      (fn [acc [x cnt]]
        (if (contains? freqs (vec (reverse x)))
          (+ acc (quot cnt 2))
          acc))
      0
      freqs)))