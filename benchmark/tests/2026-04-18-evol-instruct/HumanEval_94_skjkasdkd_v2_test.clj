(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (prime-index-sum [4 6 8 9 11 12 13 14 15]))))

(run-test test-variation)
