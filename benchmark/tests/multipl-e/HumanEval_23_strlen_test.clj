(require '[clojure.test :refer [deftest is run-test]])

(def candidate strlen)

(deftest test-humaneval

  (is (= (candidate "") 0))
  (is (= (candidate "x") 1))
  (is (= (candidate "asdasnakj") 9))
)

(run-test test-humaneval)