(defn common-key
  "Return true if the two maps share at least one common key.

  Examples:
  (common-key {:a 1 :b 2} {:c 3 :b 9}) ;=> true
  (common-key {:a 1} {:c 3 :d 4})      ;=> false
  (common-key nil {:a 1})              ;=> false
  (common-key {} {})                   ;=> false"
  [map1 map2]
  (boolean (some (set (keys (or map1 {}))) (keys (or map2 {})))))