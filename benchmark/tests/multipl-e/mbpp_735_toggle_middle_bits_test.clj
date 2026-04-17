(require '[clojure.test :refer [deftest is run-test]])

(def candidate toggle_middle_bits)

(deftest test-humaneval

  (is (= (candidate 9) 15))
  (is (= (candidate 10) 12))
  (is (= (candidate 11) 13))
  (is (= (candidate 65) 127))
  (is (= (candidate 77) 115))
)

(run-test test-humaneval)