(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 6 (max-prefix-sum [1 2 3])))
  (is (= -5 (max-prefix-sum [-5 -2 -8])))
  (is (= nil (max-prefix-sum []))))

(run-test test-variation)
