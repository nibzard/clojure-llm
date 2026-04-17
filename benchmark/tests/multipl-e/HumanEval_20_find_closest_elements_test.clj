(require '[clojure.test :refer [deftest is run-test]])

(def candidate find_closest_elements)

(deftest test-humaneval

  (is (= (candidate [1.0 2.0 3.9 4.0 5.0 2.2]) [3.9 4.0]))
  (is (= (candidate [1.0 2.0 5.9 4.0 5.0]) [5.0 5.9]))
  (is (= (candidate [1.0 2.0 3.0 4.0 5.0 2.2]) [2.0 2.2]))
  (is (= (candidate [1.0 2.0 3.0 4.0 5.0 2.0]) [2.0 2.0]))
  (is (= (candidate [1.1 2.2 3.1 4.1 5.1]) [2.2 3.1]))
)

(run-test test-humaneval)