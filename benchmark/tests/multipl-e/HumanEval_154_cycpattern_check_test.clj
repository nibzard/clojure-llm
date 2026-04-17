(require '[clojure.test :refer [deftest is run-test]])

(def candidate cycpattern_check)

(deftest test-humaneval

  (is (= (candidate "xyzw" "xyw") false))
  (is (= (candidate "yello" "ell") true))
  (is (= (candidate "whattup" "ptut") false))
  (is (= (candidate "efef" "fee") true))
  (is (= (candidate "abab" "aabb") false))
  (is (= (candidate "winemtt" "tinem") true))
)

(run-test test-humaneval)