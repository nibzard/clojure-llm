(defn running-products
  "Return a lazy sequence of running products for the input collection.

Examples:
  (running-products [1 2 3 4]) => (1 2 6 24)
  (running-products []) => ()
  (running-products nil) => ()

Works with any finite or infinite sequence of numbers."
  [xs])