(require '[clojure.test :refer [deftest is run-test]])

(def candidate unique_digits)

(deftest test-humaneval

  (is (= (candidate [15 33 1422 1]) [1 15 33]))
  (is (= (candidate [152 323 1422 10]) []))
  (is (= (candidate [12345 2033 111 151]) [111 151]))
  (is (= (candidate [135 103 31]) [31 135]))
)

(run-test test-humaneval)