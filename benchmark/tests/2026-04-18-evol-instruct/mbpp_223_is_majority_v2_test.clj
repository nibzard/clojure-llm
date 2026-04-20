(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (majority-by [1 2 2 2 3])))
  (is (= "a" (majority-by ["a" "b" "a" "a"])))
  (is (= nil (majority-by nil))))

(run-test test-variation)
