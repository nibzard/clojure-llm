(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_nonagonal)

(deftest test-humaneval

  (is (= (candidate 10) 325))
  (is (= (candidate 15) 750))
  (is (= (candidate 18) 1089))
)

(run-test test-humaneval)