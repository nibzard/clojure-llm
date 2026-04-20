(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (valid-frequencies? [1 2 2 3])))
  (is (= false (valid-frequencies? [11 11 11])))
  (is (= true (valid-frequencies? [nil 5 5 nil]))))

(run-test test-variation)
