(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [3 4 5] (longest-subseq [[1 2] [3 4 5] [6]])))
  (is (= '(:b :c) (longest-subseq '((:a) (:b :c) (:d :e)))))
  (is (= [nil 2 3] (longest-subseq [[1] [nil 2 3] [4 5]]))))

(run-test test-variation)
