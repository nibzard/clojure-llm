(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (median_of_many [3 1 2])))
  (is (= 2.5 (median_of_many [1 2 3 4])))
  (is (= nil (median_of_many nil)))
  (is (= nil (median_of_many []))))

(run-test test-variation)
