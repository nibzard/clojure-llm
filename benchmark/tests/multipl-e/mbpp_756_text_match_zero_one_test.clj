(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_match_zero_one)

(deftest test-humaneval

  (is (= (candidate "ac") false))
  (is (= (candidate "dc") false))
  (is (= (candidate "abbbba") true))
  (is (= (candidate "dsabbbba") true))
  (is (= (candidate "asbbbba") false))
  (is (= (candidate "abaaa") true))
)

(run-test test-humaneval)