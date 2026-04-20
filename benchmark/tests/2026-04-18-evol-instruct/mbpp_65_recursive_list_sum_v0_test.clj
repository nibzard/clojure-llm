(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 15 (sum-deep [1 [2 nil] {:a 3} "x" #{4 5}])))
  (is (= 0 (sum-deep nil)))
  (is (= 4.5 (sum-deep [1 :a "2" [3.5]]))))

(run-test test-variation)
