(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_match_one)

(deftest test-humaneval

  (is (= (candidate "ac") false))
  (is (= (candidate "dc") false))
  (is (= (candidate "abba") true))
)

(run-test test-humaneval)