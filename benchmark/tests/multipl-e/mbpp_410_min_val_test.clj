(require '[clojure.test :refer [deftest is run-test]])

(def candidate min_val)

(deftest test-humaneval

  (is (= (candidate ["Python" 3 2 4 5 "version"]) 2))
  (is (= (candidate ["Python" 15 20 25]) 15))
  (is (= (candidate ["Python" 30 20 40 50 "version"]) 20))
)

(run-test test-humaneval)