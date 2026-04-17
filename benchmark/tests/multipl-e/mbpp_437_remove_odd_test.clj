(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_odd)

(deftest test-humaneval

  (is (= (candidate "python") "yhn"))
  (is (= (candidate "program") "rga"))
  (is (= (candidate "language") "agae"))
)

(run-test test-humaneval)