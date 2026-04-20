(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (median-of-coll [3 1 2])))
  (is (= 7 (median-of-coll [10 nil 4 7])))
  (is (= nil (median-of-coll []))))

(run-test test-variation)
