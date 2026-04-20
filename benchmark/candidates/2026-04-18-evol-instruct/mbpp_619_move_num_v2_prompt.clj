(defn move-num
  "Move all numeric characters to the end of the vector while preserving the order of the other elements.

  Works with any vector of mixed values. Nil values are kept in place relative to the non-numeric items.

  Examples:
  (move-num [1 :a \"x\" 2 nil 3])
  => [:a \"x\" nil 1 2 3]

  (move-num [\"a\" 10 :b 20])
  => [\"a\" :b 10 20]"
  [v])