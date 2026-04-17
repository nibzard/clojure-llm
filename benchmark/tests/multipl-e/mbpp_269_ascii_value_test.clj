(require '[clojure.test :refer [deftest is run-test]])

(def candidate ascii_value)

(deftest test-humaneval

  (is (= (candidate "A") 65))
  (is (= (candidate "R") 82))
  (is (= (candidate "S") 83))
)

(run-test test-humaneval)