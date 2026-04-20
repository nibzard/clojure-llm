(defn sort-by-keywords
  "Return a vector of maps sorted by a keyword key in ascending order.

  Nil values sort after non-nil values.

  Examples:
  (sort-by-keywords :age [{:name \"A\" :age 30} {:name \"B\" :age 20}])
  ;; => [{:name \"B\" :age 20} {:name \"A\" :age 30}]

  (sort-by-keywords :score [{:id 1} {:id 2 :score 10}])
  ;; => [{:id 2 :score 10} {:id 1}]"
  [k items])