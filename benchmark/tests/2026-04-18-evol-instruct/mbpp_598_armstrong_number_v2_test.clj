(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (armstrong_number [1 5 3])))
  (is (= false (armstrong_number '(1 0 0))))
  (is (= false (armstrong_number (range 3)))))

(run-test test-variation)
