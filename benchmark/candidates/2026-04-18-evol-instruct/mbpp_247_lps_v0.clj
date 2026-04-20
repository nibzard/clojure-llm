(defn lps-nil-safe
  "Return the length of the longest palindromic subsequence in a sequence of characters.

  The input may be a string, a vector of characters, or nil.
  Return 0 for nil or an empty collection.

  Examples:
  (lps-nil-safe \"bbbab\") => 4
  (lps-nil-safe [\\a \\b \\c \\b \\a]) => 5
  (lps-nil-safe nil) => 0"
  [xs]
  (let [v (vec (or xs []))
        n (count v)]
    (if (zero? n)
      0
      (let [dp (atom (vec (repeat n (vec (repeat n 0)))))]
        (doseq [i (range n)]
          (swap! dp assoc-in [i i] 1))
        (doseq [len (range 2 (inc n))]
          (doseq [i (range 0 (- (inc n) len))]
            (let [j (+ i len -1)]
              (swap! dp assoc-in [i j]
                     (if (= (v i) (v j))
                       (+ 2 (if (= len 2) 0 (get-in @dp [(inc i) (dec j)])))
                       (max (get-in @dp [(inc i) j])
                            (get-in @dp [i (dec j)])))))))
        (get-in @dp [0 (dec n)]))))