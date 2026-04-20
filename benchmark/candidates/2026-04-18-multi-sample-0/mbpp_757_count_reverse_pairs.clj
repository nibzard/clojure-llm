(defn count_reverse_pairs
  "	Write a function to count the pairs of reverse strings in the given string list. https://www.geeksforgeeks.org/cljthon-program-to-count-the-pairs-of-reverse-strings/"
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce
      (fn [count [s n]]
        (let [r (apply str (reverse s))]
          (cond
            (= s r) (+ count (quot n 2))
            (< (compare s r) 0) (+ count (min n (get freqs r 0)))
            :else count)))
      0
      freqs)))