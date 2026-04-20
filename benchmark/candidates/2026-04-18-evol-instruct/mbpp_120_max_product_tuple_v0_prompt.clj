(defn max-product-pair
  "Return the maximum product of any two numbers in the input collection.

  Works with any sequential collection and safely handles nil or collections
  with fewer than 2 elements by returning nil.

  Examples:
  (max-product-pair [1 2 3 4]) ;=> 12
  (max-product-pair [-10 5 2])  ;=> 50
  (max-product-pair nil)       ;=> nil"
  [nums])