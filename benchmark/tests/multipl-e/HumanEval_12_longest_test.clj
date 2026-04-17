(require '[clojure.test :refer [deftest is run-test]])

(def candidate longest)

(deftest test-humaneval

  (is (= (candidate []) nil))
  (is (= (candidate ["x" "y" "z"]) "x"))
  (is (= (candidate ["x" "yyy" "zzzz" "www" "kkkk" "abc"]) "zzzz"))
)

(run-test test-humaneval)