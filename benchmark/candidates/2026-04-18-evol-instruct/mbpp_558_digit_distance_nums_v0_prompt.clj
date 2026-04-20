(defn digit-distance-vectors
  "Return the sum of absolute differences between corresponding elements of two vectors of integers.

  The vectors may be different lengths; missing positions are treated as 0.

  Examples:
  (digit-distance-vectors [1 2 3] [4 5 6]) ;=> 9
  (digit-distance-vectors [1 2] [1 2 3 4]) ;=> 7
  (digit-distance-vectors [] [7 -2]) ;=> 9"
  [v1 v2])