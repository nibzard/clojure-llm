(require '[clojure.test :refer [deftest is run-test]])

(def candidate closest_integer)

(deftest test-humaneval

  (is (= (candidate "10") 10))
  (is (= (candidate "14.5") 15))
  (is (= (candidate "-15.5") -16))
  (is (= (candidate "15.3") 15))
  (is (= (candidate "0") 0))
)

(run-test test-humaneval)