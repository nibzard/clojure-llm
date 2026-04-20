(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (validate [22 212 321])))
  (is (= false (validate [11 12 20])))
  (is (= true (validate [nil 22 nil]))))

(run-test test-variation)
