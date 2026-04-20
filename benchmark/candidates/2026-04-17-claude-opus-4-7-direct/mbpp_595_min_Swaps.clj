(defn min-swaps
  "Count minimum number of swaps required to convert one binary number represented as a string to another."
  [str1 str2]
  (if (not= (count str1) (count str2))
    -1
    (let [diffs (filter identity (map not= str1 str2))
          mismatches (transduce (filter identity)
                                (completing (fn [[c1 c2] [s1 s2]]
                                              (if (= s1 \1)
                                                [(inc c1) c2]
                                                [c1 (inc c2)])))
                                [0 0]
                                (map vector str1 str2))
          [ones zeros] mismatches]
      (if (not= (rem (+ ones zeros) 2) 0)
        -1
        (quot (+ ones zeros) 2)))))