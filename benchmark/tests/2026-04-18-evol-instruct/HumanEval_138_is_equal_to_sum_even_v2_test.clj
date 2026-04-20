(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= false (can-be-expressed-as-sum-of-four-odds 4)))
  (is (= true (can-be-expressed-as-sum-of-four-odds 8)))
  (is (= true (can-be-expressed-as-sum-of-four-odds 16))))

(run-test test-variation)
