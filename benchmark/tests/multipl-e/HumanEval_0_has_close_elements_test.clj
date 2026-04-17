(require '[clojure.test :refer [deftest is run-test]])

(def candidate has_close_elements)

(deftest test-humaneval

  (is (= (candidate [1.0 2.0 3.9 4.0 5.0 2.2] 0.3) true))
  (is (= (candidate [1.0 2.0 3.9 4.0 5.0 2.2] 0.05) false))
  (is (= (candidate [1.0 2.0 5.9 4.0 5.0] 0.95) true))
  (is (= (candidate [1.0 2.0 5.9 4.0 5.0] 0.8) false))
  (is (= (candidate [1.0 2.0 3.0 4.0 5.0 2.0] 0.1) true))
  (is (= (candidate [1.1 2.2 3.1 4.1 5.1] 1.0) true))
  (is (= (candidate [1.1 2.2 3.1 4.1 5.1] 0.5) false))
)

(run-test test-humaneval)