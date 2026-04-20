(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (armstrong-number? 153)))
  (is (= false (armstrong-number? 10)))
  (is (= true (armstrong-number? 9474))))

(run-test test-variation)
