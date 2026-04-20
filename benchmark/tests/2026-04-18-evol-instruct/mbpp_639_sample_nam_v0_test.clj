(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (sum-upper-name-lengths ["Alice" "bob" "Eve"])))
  (is (= 0 (sum-upper-name-lengths nil)))
  (is (= 3 (sum-upper-name-lengths ["" "Zoe" "anna"]))))

(run-test test-variation)
