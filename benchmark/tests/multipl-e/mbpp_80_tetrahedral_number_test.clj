(require '[clojure.test :refer [deftest is run-test]])

(def candidate tetrahedral_number)

(deftest test-humaneval

  (is (= (candidate 5) 35))
  (is (= (candidate 6) 56))
  (is (= (candidate 7) 84))
)

(run-test test-humaneval)