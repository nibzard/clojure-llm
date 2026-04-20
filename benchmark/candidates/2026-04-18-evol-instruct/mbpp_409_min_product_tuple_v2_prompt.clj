(defn min_product_infinite
  "Return the minimum product of adjacent elements in a sequence.

  The input may be finite or infinite. If the sequence has fewer than 2 elements,
  return nil.

  Examples:
  (min_product_infinite [3 7 2 5]) ;=> 10
  (min_product_infinite (range 1 1000000)) ;=> 2
  (min_product_infinite [4]) ;=> nil"
  [xs])