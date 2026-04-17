(require '[clojure.test :refer [deftest is run-test]])

(def candidate all_Characters_Same)

(deftest test-humaneval

  (is (= (candidate "python") false))
  (is (= (candidate "aaa") true))
  (is (= (candidate "data") false))
)

(run-test test-humaneval)