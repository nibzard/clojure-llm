(require '[clojure.test :refer [deftest is run-test]])

(def candidate fruit_distribution)

(deftest test-humaneval

  (is (= (candidate "5 apples and 6 oranges" 19) 8))
  (is (= (candidate "5 apples and 6 oranges" 21) 10))
  (is (= (candidate "0 apples and 1 oranges" 3) 2))
  (is (= (candidate "1 apples and 0 oranges" 3) 2))
  (is (= (candidate "2 apples and 3 oranges" 100) 95))
  (is (= (candidate "2 apples and 3 oranges" 5) 0))
  (is (= (candidate "1 apples and 100 oranges" 120) 19))
)

(run-test test-humaneval)