(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (majority-index [1 1 1 2 3])))
  (is (= 1 (majority-index [1 2 2 2 3])))
  (is (= nil (majority-index [1 2 3 4])))
  (is (= nil (majority-index []))))

(run-test test-variation)
