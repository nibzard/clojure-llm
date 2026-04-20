(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (fizz_buzz 50)))
  (is (= 2 (fizz_buzz 78)))
  (is (= 3 (fizz_buzz 79)))
  (is (= 0 (fizz_buzz nil))))

(run-test test-variation)
