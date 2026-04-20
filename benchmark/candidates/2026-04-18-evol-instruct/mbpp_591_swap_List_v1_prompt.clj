(defn swap-extremes
  "Return a vector with the first and last elements swapped.

  Works with any sequential input, including nil and empty collections.

  Examples:
  (swap-extremes [1 2 3 4]) ;=> [4 2 3 1]
  (swap-extremes '(a b c))   ;=> [c b a]
  (swap-extremes [42])       ;=> [42]"
  [coll])