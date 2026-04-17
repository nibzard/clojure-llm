(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_lowercase_underscore)

(deftest test-humaneval

  (is (= (candidate "aab_cbbbc") true))
  (is (= (candidate "aab_Abbbc") false))
  (is (= (candidate "Aaab_abbbc") false))
)

(run-test test-humaneval)