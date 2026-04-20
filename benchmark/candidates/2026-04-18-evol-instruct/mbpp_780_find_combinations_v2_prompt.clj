(defn find_combinations_lazy
  "Return a lazy sequence of all subsets of the input collection whose elements sum to target.
  Each combination should preserve the original order of elements.

  Examples:
  (find_combinations_lazy 5 [1 2 3 4]) ;=> ([1 4] [2 3])
  (find_combinations_lazy 0 [1 2 3])   ;=> (())"
  [target coll])