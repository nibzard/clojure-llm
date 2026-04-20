(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (prime-count [1 2 2 3 3 3])))
  (is (= true (prime-count '("a" "b" "c" "c"))))
  (is (= false (prime-count [])))
  (is (= false (prime-count (range 10)))))

(run-test test-variation)
