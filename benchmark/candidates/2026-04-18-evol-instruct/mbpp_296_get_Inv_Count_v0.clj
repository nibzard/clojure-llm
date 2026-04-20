(defn count-descents
  "Return the number of times an element is greater than the next element in a sequence.

  Works with any sequential collection, returns 0 for nil or collections with fewer than 2 elements.

  Examples:
  (count-descents [1 3 2 5 4]) ;=> 2
  (count-descents [5 4 3])     ;=> 2
  (count-descents nil)         ;=> 0"
  [xs]
  (let [s (seq xs)]
    (if (and s (next s))
      (count (filter true? (map > s (rest s))))
      0)))