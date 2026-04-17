(require '[clojure.test :refer [deftest is run-test]])

(def candidate replace_char)

(deftest test-humaneval

  (is (= (candidate "polygon" "y" "l") "pollgon"))
  (is (= (candidate "character" "c" "a") "aharaater"))
  (is (= (candidate "python" "l" "a") "python"))
)

(run-test test-humaneval)