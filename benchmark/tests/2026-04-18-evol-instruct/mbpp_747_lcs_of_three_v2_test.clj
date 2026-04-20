(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \e] (lcs-of-seqs "abcde" "ace" "aec"))))

(run-test test-variation)
