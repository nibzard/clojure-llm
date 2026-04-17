(require '[clojure.test :refer [deftest is run-test]])

(def candidate drop_empty)

(deftest test-humaneval

  (is (= (candidate {"c1" "Red" "c2" "Green" "c3" nil}) {"c1" "Red" "c2" "Green"}))
  (is (= (candidate {"c1" "Red" "c2" nil "c3" nil}) {"c1" "Red"}))
  (is (= (candidate {"c1" nil "c2" "Green" "c3" nil}) {"c2" "Green"}))
)

(run-test test-humaneval)