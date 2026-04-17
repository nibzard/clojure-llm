(require '[clojure.test :refer [deftest is run-test]])

(def candidate noprofit_noloss)

(deftest test-humaneval

  (is (= (candidate 1500 1200) false))
  (is (= (candidate 100 100) true))
  (is (= (candidate 2000 5000) false))
)

(run-test test-humaneval)