(defn max-cumulative-drop
  "Return the maximum cumulative drop in a sequence of numbers.

  The cumulative drop is the largest positive difference between a value
  and a later smaller value, considering only the order of the sequence.

  Examples:
  (max-cumulative-drop [10 7 5 8 3]) ;=> 7  ; 10 -> 3
  (max-cumulative-drop [5 4 3 2])    ;=> 3  ; 5 -> 2
  (max-cumulative-drop [1 2 3])      ;=> 0  ; no drop
  (max-cumulative-drop [])           ;=> 0"
  [nums])