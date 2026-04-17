(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_Rotations)

(deftest test-humaneval

  (is (= (candidate "aaaa") 1))
  (is (= (candidate "ab") 2))
  (is (= (candidate "abc") 3))
)

(run-test test-humaneval)