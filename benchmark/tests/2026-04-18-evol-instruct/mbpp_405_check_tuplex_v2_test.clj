(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (contains-subvector? [2 3] [1 2 3 4]))))

(run-test test-variation)
