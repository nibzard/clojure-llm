(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [x (or X "")
        y (or Y "")
        z (or Z "")
        m (count x)
        n (count y)
        o (count z)
        idxs (for [i (range 1 (inc m))
                   j (range 1 (inc n))
                   k (range 1 (inc o))]
               [i j k])
        dp (reduce
            (fn [memo [i j k]]
              (assoc memo
                     [i j k]
                     (if (= (nth x (dec i))
                            (nth y (dec j))
                            (nth z (dec k)))
                       (inc (get memo [(dec i) (dec j) (dec k)] 0))
                       (max (get memo [(dec i) j k] 0)
                            (get memo [i (dec j) k] 0)
                            (get memo [i j (dec k)] 0)))))
            {}
            idxs)]
    (get dp [m n o] 0)))