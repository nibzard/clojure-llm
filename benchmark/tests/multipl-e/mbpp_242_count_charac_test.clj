(require '[clojure.test :refer [deftest is run-test]])

(def candidate count_charac)

(deftest test-humaneval

  (is (= (candidate "python programming") 18))
  (is (= (candidate "language") 8))
  (is (= (candidate "words") 5))
)

(run-test test-humaneval)