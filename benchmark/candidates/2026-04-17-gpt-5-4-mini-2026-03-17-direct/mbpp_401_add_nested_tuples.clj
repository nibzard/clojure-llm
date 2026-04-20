(defn add_nested_tuples
  "Write a function to perform index wise addition of list elements in the given two nested lists."
  [test_tup1 test_tup2]
  (mapv (fn [a b]
          (if (and (sequential? a) (sequential? b))
            (add_nested_tuples a b)
            (+ a b)))
        test_tup1
        test_tup2))