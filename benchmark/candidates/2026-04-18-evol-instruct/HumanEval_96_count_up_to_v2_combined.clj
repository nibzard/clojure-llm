(defn count_up_to
  "Return a vector of all prime numbers less than or equal to n.

  The function must work correctly for nil, negative numbers, and zero.
  If n is nil or less than 2, return an empty vector.

  Examples:
  >>> (count_up_to 1)
  []
  >>> (count_up_to 2)
  [2]
  >>> (count_up_to 10)
  [2 3 5 7]
  >>> (count_up_to nil)
  []
  >>> (count_up_to -5)
  []"
  [n]
  (if (and (some? n) (>= n 2))
    (vec (filter #(and (> % 1)
                      (not-any? #(zero? (mod %1 %))
                                (range 2 (inc (Math/sqrt %)))))
                (range 2 (inc n))))
    []))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [] (count_up_to nil)))
  (is (= [] (count_up_to -5)))
  (is (= [] (count_up_to 1)))
  (is (= [2] (count_up_to 2)))
  (is (= [2 3 5 7] (count_up_to 10))))

(run-test test-variation)
