(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (longest-palindromic-prefix-len "racecarxyz")))
  (is (= 3 (longest-palindromic-prefix-len "abac")))
  (is (= 1 (longest-palindromic-prefix-len "abc")))
  (is (= 0 (longest-palindromic-prefix-len nil))))

(run-test test-variation)
