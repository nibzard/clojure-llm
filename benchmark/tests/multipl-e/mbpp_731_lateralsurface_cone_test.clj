(require '[clojure.test :refer [deftest is run-test]])

(def candidate lateralsurface_cone)

(deftest test-humaneval

  (is (= (candidate 5 12) 204.20352248333654))
  (is (= (candidate 10 15) 566.3586699569488))
  (is (= (candidate 19 17) 1521.8090132193388))
)

(run-test test-humaneval)