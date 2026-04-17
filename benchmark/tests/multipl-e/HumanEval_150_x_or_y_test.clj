(require '[clojure.test :refer [deftest is run-test]])

(def candidate x_or_y)

(deftest test-humaneval

  (is (= (candidate 7 34 12) 34))
  (is (= (candidate 15 8 5) 5))
  (is (= (candidate 3 33 5212) 33))
  (is (= (candidate 1259 3 52) 3))
  (is (= (candidate 7919 -1 12) -1))
  (is (= (candidate 3609 1245 583) 583))
  (is (= (candidate 91 56 129) 129))
  (is (= (candidate 6 34 1234) 1234))
  (is (= (candidate 1 2 0) 0))
  (is (= (candidate 2 2 0) 2))
)

(run-test test-humaneval)