(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \t (get-char [\A \B \C])))
  (is (= \s (get-char "abc")))
  (is (= \a (get-char nil))))

(run-test test-variation)
