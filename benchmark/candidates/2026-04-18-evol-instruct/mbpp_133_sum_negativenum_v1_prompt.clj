(defn sum_negative-stream
  "Return the sum of all negative numbers in `nums`.

  Works with any seqable collection, including lazy and infinite sequences.

  Examples:
  (sum_negative-stream [1 -2 3 -4]) ;=> -6
  (sum_negative-stream nil)         ;=> 0
  (sum_negative-stream (range))     ;=> 0"
  [nums])