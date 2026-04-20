(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= nil (closest_num 1)))
  (is (= 1 (closest_num 2)))
  (is (= 8 (closest_num 10))))

(run-test test-variation)
