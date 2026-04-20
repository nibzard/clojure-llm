(defn closest_num
  "Given a possibly-infinite lazy sequence of numbers and a target n, return the
  closest smaller number than n.

  If there are multiple values equally close, return the first one encountered.
  Return nil if no such number exists.

  Examples:
  (closest_num [10 3 8 1] 7) => 3
  (closest_num (range) 5) => 4
  (closest_num [9 2 6 4] 5) => 4
  (closest_num [1 2 3] 0) => nil"
  [xs n]
  (first (filter #(< % n) (reverse (take-while #(< % n) (reverse (if (sequential? xs) xs (seq xs))))))))