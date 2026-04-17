(require '[clojure.test :refer [deftest is run-test]])

(def candidate text_starta_endb)

(deftest test-humaneval

  (is (= (candidate "aabbbb") true))
  (is (= (candidate "aabAbbbc") false))
  (is (= (candidate "accddbbjjj") false))
)

(run-test test-humaneval)