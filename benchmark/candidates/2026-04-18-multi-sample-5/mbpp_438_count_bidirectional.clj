(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce
      (fn [total pair]
        (if (and (vector? pair) (= 2 (count pair)))
          (let [[a b] pair
                rev [b a]]
            (if (and (not= pair rev) (contains? freqs rev))
              (+ total (* (freqs pair) (freqs rev)))
              total))
          total))
      0
      (keys freqs))))