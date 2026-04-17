(require '[clojure.test :refer [deftest is run-test]])

(def candidate same_chars)

(deftest test-humaneval

  (is (= (candidate "eabcdzzzz" "dddzzzzzzzddeddabc") true))
  (is (= (candidate "abcd" "dddddddabc") true))
  (is (= (candidate "dddddddabc" "abcd") true))
  (is (= (candidate "eabcd" "dddddddabc") false))
  (is (= (candidate "abcd" "dddddddabcf") false))
  (is (= (candidate "eabcdzzzz" "dddzzzzzzzddddabc") false))
  (is (= (candidate "aabb" "aaccc") false))
)

(run-test test-humaneval)