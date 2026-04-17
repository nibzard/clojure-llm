(require '[clojure.test :refer [deftest is run-test]])

(def candidate catalan_number)

(deftest test-humaneval

  (is (= (candidate 10) 16796))
  (is (= (candidate 9) 4862))
  (is (= (candidate 7) 429))
)

(run-test test-humaneval)