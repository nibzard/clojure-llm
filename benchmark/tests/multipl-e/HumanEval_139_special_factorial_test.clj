(require '[clojure.test :refer [deftest is run-test]])

(def candidate special_factorial)

(deftest test-humaneval

  (is (= (candidate 4) 288))
  (is (= (candidate 5) 34560))
  (is (= (candidate 7) 125411328000))
  (is (= (candidate 1) 1))
)

(run-test test-humaneval)