(require '[clojure.test :refer [deftest is run-test]])

(def candidate lateralsurface_cube)

(deftest test-humaneval

  (is (= (candidate 5) 100))
  (is (= (candidate 9) 324))
  (is (= (candidate 10) 400))
)

(run-test test-humaneval)