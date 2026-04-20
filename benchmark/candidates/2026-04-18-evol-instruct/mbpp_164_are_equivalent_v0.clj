(defn common-prefix?
  "Return true if the two inputs share the same first element, otherwise false.

  Works with any sequential collections.

  Examples:
  (common-prefix? [1 2 3] [1 9 8]) => true
  (common-prefix? '(\"a\" \"b\") '(\"a\" \"c\")) => true
  (common-prefix? [] [1 2]) => false
  (common-prefix? nil [1 2]) => false"
  [xs ys]
  (let [a (first xs)
        b (first ys)]
    (and (some? a) (= a b))))