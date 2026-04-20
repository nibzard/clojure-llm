(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce
      (fn [acc pair]
        (if (and (vector? pair) (= 2 (count pair)))
          (let [[a b] pair
                rev [b a]]
            (if (= pair rev)
              (+ acc (quot (get freqs pair 0) 2))
              (+ acc (min (get freqs pair 0)
                          (get freqs rev 0)))))
          acc))
      0
      (filter (fn [[a b]] (<= (compare a b) 0))
              (keys freqs)))))