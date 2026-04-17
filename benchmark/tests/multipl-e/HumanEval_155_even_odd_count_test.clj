(require '[clojure.test :refer [deftest is run-test]])

(def candidate even_odd_count)

(deftest test-humaneval

  (is (= (candidate 7) [0 1]))
  (is (= (candidate -78) [1 1]))
  (is (= (candidate 3452) [2 2]))
  (is (= (candidate 346211) [3 3]))
  (is (= (candidate -345821) [3 3]))
  (is (= (candidate -2) [1 0]))
  (is (= (candidate -45347) [2 3]))
  (is (= (candidate 0) [1 0]))
)

(run-test test-humaneval)