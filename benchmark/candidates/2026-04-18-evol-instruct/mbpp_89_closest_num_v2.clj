(defn closest-num-in-seq
  "Return the closest smaller number to n from a collection of numbers.

  If multiple numbers are equally close, return the larger one.
  Return nil when no number in nums is smaller than n.

  Examples:
  (closest-num-in-seq 10 [3 8 9 12])  ;=> 9
  (closest-num-in-seq 5 [7 6 10])     ;=> nil
  (closest-num-in-seq 20 '(1 19 18 5)) ;=> 19"
  [n nums]
  (when-let [candidates (seq (filter #(< % n) nums))]
    (apply max-key #(Math/abs ^long (- n %)) candidates)))