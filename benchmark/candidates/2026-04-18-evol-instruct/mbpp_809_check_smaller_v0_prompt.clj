(defn every-prefix-smaller?
  "Return true if every prefix sum of the second vector is strictly smaller than the corresponding prefix sum of the first vector.

Examples:
  (every-prefix-smaller? [3 5 10] [1 4 8]) ;=> true
  (every-prefix-smaller? [3 5 10] [1 4 9]) ;=> false
  (every-prefix-smaller? [] []) ;=> true"
  [v1 v2])