(defn add_nested_tuples
  "	Write a function to perform index wise addition of list elements in the given two nested lists."
  [test_tup1 test_tup2]
  (letfn [(add-elements [a b]
            (cond
              (and (sequential? a) (sequential? b))
              (mapv add-elements a b)

              (and (number? a) (number? b))
              (+ a b)

              :else nil))]
    (add-elements test_tup1 test_tup2)))