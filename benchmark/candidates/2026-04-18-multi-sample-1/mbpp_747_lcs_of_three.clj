(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [x (or X "")
        y (or Y "")
        z (or Z "")
        m (count x)
        n (count y)
        o (count z)]
    (if (or (zero? m) (zero? n) (zero? o))
      ""
      (let [dp (reduce
                 (fn [dp i]
                   (reduce
                     (fn [dp j]
                       (reduce
                         (fn [dp k]
                           (let [xi (.charAt x (dec i))
                                 yj (.charAt y (dec j))
                                 zk (.charAt z (dec k))]
                             (assoc dp [i j k]
                                    (if (and (= xi yj) (= yj zk))
                                      (str (get dp [(dec i) (dec j) (dec k)] "") xi)
                                      (let [a (get dp [(dec i) j k] "")
                                            b (get dp [i (dec j) k] "")
                                            c (get dp [i j (dec k)] "")]
                                        (apply max-key count [a b c]))))))
                         dp
                         (range 1 (inc o))))
                     dp
                     (range 1 (inc n))))
                 {}
                 (range 1 (inc m)))]
        (get dp [m n o] ""))))