(defn common-element?
  "Return true if two collections share at least one common element.

  Works with any seqable collections, including vectors, lists, sets, and lazy seqs.

  Examples:
  (common-element? [1 2 3] '(4 5 2)) ;=> true
  (common-element? nil [1 2 3])      ;=> false
  (common-element? (range) [1000])   ;=> true"
  [coll1 coll2])