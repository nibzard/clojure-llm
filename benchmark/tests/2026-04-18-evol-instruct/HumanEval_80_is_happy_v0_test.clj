(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (all-distinct-triplets [1 2 3 4])))
  (is (= false (all-distinct-triplets [1 1 2 3])))
  (is (= true (all-distinct-triplets '(a b c d e))))
  (is (= false (all-distinct-triplets [1 2])))
  (is (= true (all-distinct-triplets (range 5)))))

(run-test test-variation)
