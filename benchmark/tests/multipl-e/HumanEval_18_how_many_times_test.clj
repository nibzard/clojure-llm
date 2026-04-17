(require '[clojure.test :refer [deftest is run-test]])

(def candidate how_many_times)

(deftest test-humaneval

  (is (= (candidate "" "x") 0))
  (is (= (candidate "xyxyxyx" "x") 4))
  (is (= (candidate "cacacacac" "cac") 4))
  (is (= (candidate "john doe" "john") 1))
)

(run-test test-humaneval)