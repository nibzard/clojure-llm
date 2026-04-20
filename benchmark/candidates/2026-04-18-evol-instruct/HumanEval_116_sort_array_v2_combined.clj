(defn sort_by_parity_then_value
  "Sort a collection of integers by parity first, then by value.

  All even numbers come before odd numbers. Within each parity group,
  sort numbers in ascending numeric order.

  The function must work on any sequential collection, including vectors,
  lists, and lazy sequences.

  Examples:
  >>> (sort_by_parity_then_value [3 2 7 4 1 6])
  [2 4 6 1 3 7]

  >>> (sort_by_parity_then_value '(-5 8 0 3 2 9))
  (0 2 8 -5 3 9)

  >>> (sort_by_parity_then_value (take 5 (iterate inc 1)))
  [2 4 1 3 5]"
  [xs]
  (let [sorted (sort-by (juxt #(mod % 2) identity) xs)]
    (if (vector? xs)
      (vec sorted)
      (if (list? xs)
        (apply list sorted)
        (vec sorted)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 4 6 1 3 7] (sort_by_parity_then_value [3 2 7 4 1 6])))
  (is (= '(0 2 8 -5 3 9) (sort_by_parity_then_value '(-5 8 0 3 2 9))))
  (is (= [2 4 1 3 5] (sort_by_parity_then_value (take 5 (iterate inc 1)))))
  (is (= [] (sort_by_parity_then_value []))))

(run-test test-variation)
