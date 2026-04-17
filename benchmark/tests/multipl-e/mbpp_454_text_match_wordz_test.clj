(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_match_wordz)

(deftest test-humaneval

  (is (= (candidate "pythonz.") true))
  (is (= (candidate "xyz.") true))
  (is (= (candidate "  lang  .") false))
)

(run-test test-humaneval)