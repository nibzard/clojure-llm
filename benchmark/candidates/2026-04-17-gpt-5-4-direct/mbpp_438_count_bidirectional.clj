(defn count_bidirectional
  "	Write a function to count bidirectional vector pairs."
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce
      (fn [acc [a b :as pair]]
        (if (= a b)
          (+ acc (quot (get freqs pair 0) 2))
          (if (neg? (compare a b))
            (+ acc (min (get freqs pair 0)
                        (get freqs [b a] 0)))
            acc)))
      0
      (keys freqs))))