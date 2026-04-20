(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (lps-nil-safe "bbbab")))
  (is (= 5 (lps-nil-safe [\a \b \c \b \a])))
  (is (= 0 (lps-nil-safe nil))))

(run-test test-variation)
