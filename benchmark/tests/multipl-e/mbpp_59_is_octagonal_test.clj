(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_octagonal)

(deftest test-humaneval

  (is (= (candidate 5) 65))
  (is (= (candidate 10) 280))
  (is (= (candidate 15) 645))
)

(run-test test-humaneval)