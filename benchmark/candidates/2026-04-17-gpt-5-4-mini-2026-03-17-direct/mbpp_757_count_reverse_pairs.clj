(defn count_reverse_pairs
  "Write a function to count the pairs of reverse strings in the given string list. https://www.geeksforgeeks.org/cljthon-program-to-count-the-pairs-of-reverse-strings/"
  [test_list]
  (let [freqs (frequencies test_list)]
    (reduce
      (fn [acc s]
        (let [r (apply str (reverse s))]
          (if (and (contains? freqs r)
                   (or (neg? (compare s r))
                       (and (= s r) (pos? (get freqs s 0)))))
            (+ acc (if (= s r)
                     (quot (* (get freqs s 0) (dec (get freqs s 0))) 2)
                     (* (get freqs s 0) (get freqs r 0))))
            acc)))
      0
      (keys freqs))))