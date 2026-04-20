(defn add-if-present
  "Return a new vector with the value appended only when x is non-nil.

  Examples:
  (add-if-present [1 2] 3)    ;=> [1 2 3]
  (add-if-present [1 2] nil)  ;=> [1 2]"
  [v x])