(defn median_numbers
  "Write a function to find the median of three numbers."
  [a b c]
  (+ a b c (- (max a b c) (min a b c))))