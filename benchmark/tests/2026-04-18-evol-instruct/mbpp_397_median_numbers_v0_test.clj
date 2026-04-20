(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 5 (median-of-three-seqs [3 1 9] [8 2 7] [5 4 6])))
  (is (= 1 (median-of-three-seqs nil [10] [1 2 3])))
  (is (= nil (median-of-three-seqs [] [] []))))

(run-test test-variation)
