(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies test_list)]
    (reduce
      (fn [acc [v cnt]]
        (if (zero? (get freqs (vec (reverse v)) 0))
          acc
          (+ acc (quot cnt 2))))
      0
      freqs)))