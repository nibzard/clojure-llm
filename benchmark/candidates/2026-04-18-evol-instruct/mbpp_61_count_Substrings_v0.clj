(defn count-balanced-subvectors
  "Return the number of contiguous subvectors whose sum equals their length.

  Works on any sequential collection of integers, including nil.

  Examples:
  (count-balanced-subvectors [1 0 1]) ;=> 4
  (count-balanced-subvectors nil)     ;=> 0
  (count-balanced-subvectors [2 -1 0]) ;=> 1"
  [xs]
  (let [xs (or xs [])
        freqs (frequencies (reductions + 0 (map dec xs)))]
    (reduce + (map #(let [n %] (quot (* n (dec n)) 2)) (vals freqs)))))