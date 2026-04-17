(require '[clojure.test :refer [deftest is run-test]])

(def candidate get_Char)

(deftest test-humaneval

  (is (= (candidate "abc") "f"))
  (is (= (candidate "gfg") "t"))
  (is (= (candidate "ab") "c"))
)

(run-test test-humaneval)