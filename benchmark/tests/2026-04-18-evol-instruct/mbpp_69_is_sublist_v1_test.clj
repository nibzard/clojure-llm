(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (is-contiguous-subvector? [1 2 3 4] [2 3])))
  (is (= false (is-contiguous-subvector? [1 2 3 4] [2 4])))
  (is (= true (is-contiguous-subvector? [1 2 3] []))))

(run-test test-variation)
