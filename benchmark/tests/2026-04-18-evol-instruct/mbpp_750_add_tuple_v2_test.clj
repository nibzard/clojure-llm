(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 3] (add-if-present [1 2] 3)))
  (is (= [1 2] (add-if-present [1 2] nil))))

(run-test test-variation)
