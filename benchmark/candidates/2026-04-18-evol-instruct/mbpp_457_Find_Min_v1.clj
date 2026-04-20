(defn min-subseq
  "Return the shortest subsequence of `coll` whose elements sum to a value >= `target`.

  The function should work with any sequential collection, including vectors and lazy sequences.
  If multiple subsequences have the same minimum length, return the first one.
  Return nil if no such subsequence exists.

  Examples:
  (min-subseq 7 [2 3 1 2 4 3]) ;=> [4 3]
  (min-subseq 5 [1 1 1 1])     ;=> nil
  (min-subseq 3 '(1 2 3 4))    ;=> (1 2)"
  [target coll]
  (let [v (vec coll)
        n (count v)]
    (loop [i 0
           best nil
           best-len ##Inf]
      (if (= i n)
        best
        (let [res
              (loop [j i
                     sum 0]
                (cond
                  (>= sum target) (subvec v i j)
                  (= j n) nil
                  :else (recur (inc j) (+ sum (v j)))))]
          (if (and res (< (count res) best-len))
            (recur (inc i) res (count res))
            (recur (inc i) best best-len)))))))