(defn lowercase-keys
  "Return a map with all string keys converted to lowercase.

  Non-string keys are left unchanged.

  Examples:
  (lowercase-keys {\"A\" 1, \"B\" 2, :C 3})
  => {\"a\" 1, \"b\" 2, :C 3}

  (lowercase-keys nil)
  => nil"
  [m]
  (when m
    (into {}
          (map (fn [[k v]]
                 [(if (string? k) (.toLowerCase ^String k) k) v]))
          m)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {"a" 1, "b" 2, :C 3}
         (lowercase-keys {"A" 1, "B" 2, :C 3}))))

(run-test test-variation)
