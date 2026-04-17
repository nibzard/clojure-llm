(require '[clojure.test :refer [deftest is run-test]])

(def candidate choose_num)

(deftest test-humaneval

  (is (= (candidate 12 15) 14))
  (is (= (candidate 13 12) -1))
  (is (= (candidate 33 12354) 12354))
  (is (= (candidate 5234 5233) -1))
  (is (= (candidate 6 29) 28))
  (is (= (candidate 27 10) -1))
  (is (= (candidate 7 7) -1))
  (is (= (candidate 546 546) 546))
)

(run-test test-humaneval)