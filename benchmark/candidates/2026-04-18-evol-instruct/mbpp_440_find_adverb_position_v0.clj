(defn find_keyword_position
  "Return the first keyword in the input collection and its zero-based position.

  The input may be a vector or list of mixed values. Return nil if no keyword
  is present.

  Examples:
  (find_keyword_position [:a 1 \"x\" :b]) ;=> [:a 0]
  (find_keyword_position [1 \"x\" true])   ;=> nil"
  [coll]
  (first
    (keep-indexed
      (fn [idx x]
        (when (keyword? x)
          [x idx]))
      coll)))