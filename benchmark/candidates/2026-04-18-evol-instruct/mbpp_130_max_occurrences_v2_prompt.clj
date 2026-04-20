(defn max-occurrence
  "Return the first item with the highest frequency in a collection.

  Works with any sequential collection, including nil and empty collections.
  If multiple items tie for the highest frequency, return the one that
  appears first in the original collection.

  Examples:
  (max-occurrence [1 2 2 3 3]) ;=> 2
  (max-occurrence [:a :b :a :b]) ;=> :a
  (max-occurrence nil) ;=> nil"
  [coll])