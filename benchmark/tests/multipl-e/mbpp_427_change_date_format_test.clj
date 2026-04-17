(require '[clojure.test :refer [deftest is run-test]])

(def candidate change_date_format)

(deftest test-humaneval

  (is (= (candidate "2026-01-02") "02-01-2026"))
  (is (= (candidate "2020-11-13") "13-11-2020"))
  (is (= (candidate "2021-04-26") "26-04-2021"))
)

(run-test test-humaneval)