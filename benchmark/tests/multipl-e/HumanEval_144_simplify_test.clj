(require '[clojure.test :refer [deftest is run-test]])

(def candidate simplify)

(deftest test-humaneval

  (is (= (candidate "1/5" "5/1") true))
  (is (= (candidate "1/6" "2/1") false))
  (is (= (candidate "5/1" "3/1") true))
  (is (= (candidate "7/10" "10/2") false))
  (is (= (candidate "2/10" "50/10") true))
  (is (= (candidate "7/2" "4/2") true))
  (is (= (candidate "11/6" "6/1") true))
  (is (= (candidate "2/3" "5/2") false))
  (is (= (candidate "5/2" "3/5") false))
  (is (= (candidate "2/4" "8/4") true))
  (is (= (candidate "2/4" "4/2") true))
  (is (= (candidate "1/5" "5/1") true))
  (is (= (candidate "1/5" "1/5") false))
)

(run-test test-humaneval)