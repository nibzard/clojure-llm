(defn count-descents
  "Return the number of descents in a sequence.

  A descent is a position i such that (nth coll i) > (nth coll (inc i)).

  Works with any sequential collection that supports seqing, and returns 0
  for nil or collections with fewer than 2 elements.

  Examples:
  (count-descents [3 2 1]) ;=> 2
  (count-descents [1 2 3]) ;=> 0
  (count-descents nil)     ;=> 0"
  [coll])