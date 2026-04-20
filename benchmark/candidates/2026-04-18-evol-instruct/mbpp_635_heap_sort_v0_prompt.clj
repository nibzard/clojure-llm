(defn sort-by-frequency
  "Return the elements of coll sorted by descending frequency.
  Break ties by the original order of first appearance.

  Examples:
  (sort-by-frequency [1 2 2 3 3 3]) ;=> [3 3 3 2 2 1]
  (sort-by-frequency [:a :b :a :c :b]) ;=> [:a :a :b :b :c]
  (sort-by-frequency nil) ;=> nil"
  [coll])