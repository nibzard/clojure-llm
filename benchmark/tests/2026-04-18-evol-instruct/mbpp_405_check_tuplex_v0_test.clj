(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (contains-subvector? [2 3] [1 2 3 4])))
  (is (= true (contains-subvector? [] [1 2 3])))
  (is (= false (contains-subvector? [4 5] [1 2 3]))))

(run-test test-variation)
