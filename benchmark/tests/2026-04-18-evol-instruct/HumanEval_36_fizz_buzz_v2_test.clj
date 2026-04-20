(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '() (fizz_buzz 50)))
  (is (= '(77) (fizz_buzz 78)))
  (is (= '(77 143) (fizz_buzz 150))))

(run-test test-variation)
