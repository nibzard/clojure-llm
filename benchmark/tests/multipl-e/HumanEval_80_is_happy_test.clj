(require '[clojure.test :refer [deftest is run-test]])

(def candidate is_happy)

(deftest test-humaneval

  (is (= (candidate "a") false))
  (is (= (candidate "aa") false))
  (is (= (candidate "abcd") true))
  (is (= (candidate "aabb") false))
  (is (= (candidate "adb") true))
  (is (= (candidate "xyy") false))
  (is (= (candidate "iopaxpoi") true))
  (is (= (candidate "iopaxioi") false))
)

(run-test test-humaneval)