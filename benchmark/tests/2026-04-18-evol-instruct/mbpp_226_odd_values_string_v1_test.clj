(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "ace" (even-indexed-chars "abcdef")))
  (is (= "a" (even-indexed-chars "a")))
  (is (= "" (even-indexed-chars nil))))

(run-test test-variation)
