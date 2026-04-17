(require '[clojure.test :refer [deftest is run-test]])

(def candidate radian_degree)

(deftest test-humaneval

  (is (= (candidate 90) 1.5707963267948966))
  (is (= (candidate 60) 1.0471975511965976))
  (is (= (candidate 120) 2.0943951023931953))
)

(run-test test-humaneval)