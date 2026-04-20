(defn count_reverse_pairs
  "	Write a function to count the pairs of reverse strings in the given string list. https://www.geeksforgeeks.org/cljthon-program-to-count-the-pairs-of-reverse-strings/"
  [test_list]
  (let [freqs (frequencies (or test_list []))]
    (reduce-kv
      (fn [acc s cnt]
        (let [rs (apply str (reverse s))]
          (cond
            (= s rs) (+ acc (quot cnt 2))
            (neg? (compare s rs)) (+ acc (min cnt (get freqs rs 0)))
            :else acc)))
      0
      freqs)))