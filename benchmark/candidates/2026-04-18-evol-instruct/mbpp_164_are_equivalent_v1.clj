(defn same-multiset?
  "Return true if two collections contain the same elements with the same frequencies, regardless of order.

  Examples:
  (same-multiset? [1 2 2 3] '(3 2 1 2)) ;=> true
  (same-multiset? [:a :b] [:b :a :a])    ;=> false
  (same-multiset? nil [])                ;=> true"
  [coll1 coll2]
  (= (frequencies (or coll1 []))
     (frequencies (or coll2 []))))