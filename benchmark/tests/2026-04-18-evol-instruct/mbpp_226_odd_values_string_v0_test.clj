(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "ace" (even-indexed-chars "abcdef")))
  (is (= "hlo" (even-indexed-chars "hello")))
  (is (= "" (even-indexed-chars nil))))

(run-test test-variation)
