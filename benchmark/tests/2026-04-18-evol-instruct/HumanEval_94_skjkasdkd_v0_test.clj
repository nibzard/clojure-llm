(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (prime-digit-sum [0 3 2 1 3 5 7 4 5 5 5 2 181 32 4 32 3 2 32 324 4 3])))
  (is (= 25 (prime-digit-sum [1 0 1 8 2 4597 2 1 3 40 1 2 1 2 4 2 5 1])))
  (is (= 19 (prime-digit-sum (range 2 20))))
  (is (= 0 (prime-digit-sum [4 6 8 9 10]))))

(run-test test-variation)
