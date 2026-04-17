(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_match_wordz_middle)

(deftest test-humaneval

  (is (= (candidate "pythonzabc.") true))
  (is (= (candidate "zxyabc.") false))
  (is (= (candidate "  lang  .") false))
)

(run-test test-humaneval)