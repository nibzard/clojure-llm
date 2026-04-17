(require '[clojure.test :refer [deftest is run-test]])

(def candidate concatenate)

(deftest test-humaneval

  (is (= (candidate []) ""))
  (is (= (candidate ["x" "y" "z"]) "xyz"))
  (is (= (candidate ["x" "y" "z" "w" "k"]) "xyzwk"))
)

(run-test test-humaneval)