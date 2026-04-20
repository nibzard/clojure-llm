(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce
      (fn [acc pair]
        (if (and (vector? pair)
                 (= 2 (count pair))
                 (not= (first pair) (second pair)))
          (+ acc (min (get freqs pair 0)
                      (get freqs [(second pair) (first pair)] 0)))
          acc))
      0
      (keys freqs))))