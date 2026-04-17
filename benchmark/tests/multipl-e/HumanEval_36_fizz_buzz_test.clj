(require '[clojure.test :refer [deftest is run-test]])

(def candidate fizz_buzz)

(deftest test-humaneval

  (is (= (candidate 50) 0))
  (is (= (candidate 78) 2))
  (is (= (candidate 79) 3))
  (is (= (candidate 100) 3))
  (is (= (candidate 200) 6))
  (is (= (candidate 4000) 192))
  (is (= (candidate 10000) 639))
  (is (= (candidate 100000) 8026))
)

(run-test test-humaneval)