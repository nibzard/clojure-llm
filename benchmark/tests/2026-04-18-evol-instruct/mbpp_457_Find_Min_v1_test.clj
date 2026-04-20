(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [4 3] (min-subseq 7 [2 3 1 2 4 3])))
  (is (= nil (min-subseq 5 [1 1 1 1])))
  (is (= [1 2] (min-subseq 3 '(1 2 3 4)))))

(run-test test-variation)
