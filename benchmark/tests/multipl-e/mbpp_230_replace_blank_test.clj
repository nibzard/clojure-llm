(require '[clojure.test :refer [deftest is run-test]])

(def candidate replace_blank)

(deftest test-humaneval

  (is (= (candidate "hello people" "@") "hello@people"))
  (is (= (candidate "python program language" "$") "python$program$language"))
  (is (= (candidate "blank space" "-") "blank-space"))
)

(run-test test-humaneval)