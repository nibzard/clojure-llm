(defn total-match-nil-safe
  "Return the list of strings with the smaller total character count.
If both lists have the same total, return the first list.

This variation is nil-safe: treat nil as an empty list.

Examples:
  (total-match-nil-safe nil nil)            ;=> []
  (total-match-nil-safe [\"a\" \"bc\"] nil)    ;=> [\"a\" \"bc\"]
  (total-match-nil-safe [\"hi\"] [\"a\" \"b\"]) ;=> [\"hi\"]
  (total-match-nil-safe [\"ab\"] [\"c\" \"d\"]) ;=> [\"ab\"]"
  [lst1 lst2])