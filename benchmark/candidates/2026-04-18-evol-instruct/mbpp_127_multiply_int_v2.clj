(defn running-products
  "Return a lazy sequence of cumulative products for a collection of numbers.

  Examples:
  (running-products [1 2 3 4])
  ;; => (1 2 6 24)

  (running-products [])
  ;; => ()

  The function should work with infinite sequences as well."
  [nums]
  (reductions * nums))