(require '[clojure.test :refer [deftest is run-test]])

(def candidate do_algebra)

(deftest test-humaneval

  (is (= (candidate ["**" "*" "+"] [2 3 4 5]) 37))
  (is (= (candidate ["+" "*" "-"] [2 3 4 5]) 9))
  (is (= (candidate ["//" "*"] [7 3 4]) 8))
)

(run-test test-humaneval)