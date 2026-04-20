(defn same_keys
  "Check whether two maps have the same set of keys, ignoring values.

  Examples:
  >>> (same_keys {:a 1, :b 2, :c 3} {:c 9, :b 0, :a 42})
  true
  >>> (same_keys {:a 1, :b 2} {:a 10, :b 20, :c 30})
  false
  >>> (same_keys {} {})
  true
  >>> (same_keys nil {:a 1})
  false"
  [m0 m1])