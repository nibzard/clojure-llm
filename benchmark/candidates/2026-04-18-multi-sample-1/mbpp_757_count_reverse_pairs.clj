(defn count_reverse_pairs
  "	Write a function to count the pairs of reverse strings in the given string list. https://www.geeksforgeeks.org/cljthon-program-to-count-the-pairs-of-reverse-strings/"
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce
      (fn [count [s n]]
        (let [rs (apply str (reverse s))]
          (cond
            (= s rs) (+ count (quot n 2))
            (neg? (compare s rs)) (+ count (min n (get freqs rs 0)))
            :else count)))
      0
      freqs)))