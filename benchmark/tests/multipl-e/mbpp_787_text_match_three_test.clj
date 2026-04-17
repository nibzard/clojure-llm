(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_match_three)

(deftest test-humaneval

  (is (= (candidate "ac") false))
  (is (= (candidate "dc") false))
  (is (= (candidate "abbbba") true))
  (is (= (candidate "caacabbbba") true))
)

(run-test test-humaneval)