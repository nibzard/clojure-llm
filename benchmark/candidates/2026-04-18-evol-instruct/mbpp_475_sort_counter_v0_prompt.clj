(defn sort-by-key-desc
  "Return a map sorted by key in descending order.

  Examples:
  (sort-by-key-desc {:b 2, :a 1, :c 3})
  ;; => {:c 3, :b 2, :a 1}

  Works with nil input by returning {}."
  [m])