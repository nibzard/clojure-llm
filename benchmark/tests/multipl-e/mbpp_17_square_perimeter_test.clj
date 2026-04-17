(require '[clojure.test :refer [deftest is run-test]])

(def candidate square_perimeter)

(deftest test-humaneval

  (is (= (candidate 10) 40))
  (is (= (candidate 5) 20))
  (is (= (candidate 4) 16))
)

(run-test test-humaneval)