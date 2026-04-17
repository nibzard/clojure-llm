(require '[clojure.test :refer [deftest is run-test]])

(def candidate max_val)

(deftest test-humaneval

  (is (= (candidate ["Python" 3 2 4 5 "version"]) 5))
  (is (= (candidate ["Python" 15 20 25]) 25))
  (is (= (candidate ["Python" 30 20 40 50 "version"]) 50))
)

(run-test test-humaneval)