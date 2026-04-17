(require '[clojure.test :refer [deftest is run-test]])

(def candidate check_str)

(deftest test-humaneval

  (is (= (candidate "annie") true))
  (is (= (candidate "dawood") false))
  (is (= (candidate "Else") true))
)

(run-test test-humaneval)