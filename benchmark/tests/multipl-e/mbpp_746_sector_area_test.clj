(require '[clojure.test :refer [deftest is run-test]])

(def candidate sector_area)

(deftest test-humaneval

  (is (= (candidate 4 45) 6.283185307179586))
  (is (= (candidate 9 45) 31.808625617596654))
  (is (= (candidate 9 361) nil))
)

(run-test test-humaneval)