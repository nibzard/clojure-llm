(require '[clojure.test :refer [deftest is run-test]])

(def candidate first_non_repeating_character)

(deftest test-humaneval

  (is (= (candidate "abcabc") nil))
  (is (= (candidate "abc") "a"))
  (is (= (candidate "ababc") "c"))
)

(run-test test-humaneval)