(defn merge-nested-maps
  "Return a nested map formed by recursively merging two maps.

  For keys present in both maps:
  - If both values are maps, merge them recursively.
  - Otherwise, the value from the second map wins.

  Examples:
  (merge-nested-maps {:a 1 :b {:c 2}} {:b {:d 3} :e 4})
  ;; => {:a 1, :b {:c 2, :d 3}, :e 4}

  (merge-nested-maps nil {:a 1})
  ;; => {:a 1}"
  [m1 m2])