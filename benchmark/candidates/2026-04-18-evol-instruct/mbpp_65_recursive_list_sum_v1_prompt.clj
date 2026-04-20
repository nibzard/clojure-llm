(defn recursive_list_product
  "Return the product of all numeric elements in a nested list or vector.

  Ignore nil values, and treat empty collections as having product 1.

  Examples:
  (recursive_list_product [1 [2 nil] '(3 [4])]) ;=> 24
  (recursive_list_product [])                   ;=> 1
  (recursive_list_product [2 [0 5]])            ;=> 0"
  [data]
  )