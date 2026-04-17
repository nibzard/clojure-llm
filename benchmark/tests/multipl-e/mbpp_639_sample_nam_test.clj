(require '[clojure.test :refer [deftest is run-test]])

(def candidate sample_nam)

(deftest test-humaneval

  (is (= (candidate ["sally" "Dylan" "rebecca" "Diana" "Joanne" "keith"]) 16))
  (is (= (candidate ["php" "res" "Python" "abcd" "Java" "aaa"]) 10))
  (is (= (candidate ["abcd" "Python" "abba" "aba"]) 6))
)

(run-test test-humaneval)