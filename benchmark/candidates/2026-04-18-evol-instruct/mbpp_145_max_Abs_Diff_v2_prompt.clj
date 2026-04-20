(defn max-abs-diff-stream
  "Return the maximum absolute difference between any two elements in a potentially infinite sequence.
  
  The function must only realize as much of the sequence as needed to determine the result.
  
  Examples:
  (max-abs-diff-stream [3 8 2 10 5]) ;=> 8
  (max-abs-diff-stream [5])          ;=> 0
  (max-abs-diff-stream nil)          ;=> 0"
  [xs])