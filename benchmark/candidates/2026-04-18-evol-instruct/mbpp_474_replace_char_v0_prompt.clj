(defn replace-elements
  "Return a new vector with every occurrence of old-val replaced by new-val.

  Examples:
  (replace-elements [1 2 1 3] 1 9) ;=> [9 2 9 3]
  (replace-elements [] :a :b)      ;=> []
  (replace-elements [nil 2 nil] nil :x) ;=> [:x 2 :x]"
  [v old-val new-val])