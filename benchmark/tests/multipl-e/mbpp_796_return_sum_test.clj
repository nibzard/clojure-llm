(require '[clojure.test :refer [deftest is run-test]])

(def candidate return_sum)

(deftest test-humaneval

  (is (= (candidate {"a" 100 "b" 200 "c" 300}) 600))
  (is (= (candidate {"a" 25 "b" 18 "c" 45}) 88))
  (is (= (candidate {"a" 36 "b" 39 "c" 49}) 124))
)

(run-test test-humaneval)