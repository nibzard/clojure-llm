(require '[clojure.test :refer [deftest is run-test]])

(def candidate empty_list)

(deftest test-humaneval

  (is (= (candidate 5) [{} {} {} {} {}]))
  (is (= (candidate 6) [{} {} {} {} {} {}]))
  (is (= (candidate 7) [{} {} {} {} {} {} {}]))
)

(run-test test-humaneval)