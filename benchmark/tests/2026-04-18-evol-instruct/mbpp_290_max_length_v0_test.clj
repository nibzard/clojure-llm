(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:b :c] (longest-subseq [[:a] [:b :c] [:d]])))
  (is (= [1 2 3 4] (longest-subseq [[1 2] [1 2 3 4] [5]])))
  (is (= nil (longest-subseq []))))

(run-test test-variation)
