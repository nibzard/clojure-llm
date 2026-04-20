(defn median-of-three-seqs
  "Return the median value among the first elements of three sequences.

Examples:
(median-of-three-seqs [3 1 9] [8 2 7] [5 4 6]) ;=> 5
(median-of-three-seqs nil [10] [1 2 3])        ;=> 1
(median-of-three-seqs [] [7 8] [4 5])          ;=> nil

Treat nil or empty sequences as missing values. If fewer than three values are present,
return the median of the available values; if no values are present, return nil."
  [s1 s2 s3]
  (let [vals (keep first [s1 s2 s3])
        sorted (sort vals)
        n (count sorted)]
    (cond
      (zero? n) nil
      (= 1 n) (first sorted)
      (= 2 n) (second sorted)
      :else (nth sorted 1))))