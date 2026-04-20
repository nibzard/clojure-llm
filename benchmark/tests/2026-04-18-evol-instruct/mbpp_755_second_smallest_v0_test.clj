(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (second-smallest-distinct [3 1 2 1])))
  (is (= nil (second-smallest-distinct [5 5 5])))
  (is (= 4 (second-smallest-distinct [nil 4 2 4]))))

(run-test test-variation)
