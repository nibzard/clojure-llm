(defn max_abs_product
  "Return the maximum absolute product of any two numbers in the collection.

  Works with finite or infinite lazy sequences, so it should only realize
  as much input as needed.

  Examples:
  (max_abs_product [1 -3 2 4]) ;=> 12
  (max_abs_product [-10 2 -5]) ;=> 50
  (max_abs_product nil) ;=> nil"
  [xs])