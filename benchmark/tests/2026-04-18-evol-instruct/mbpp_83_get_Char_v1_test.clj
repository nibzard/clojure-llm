(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \A (xor-char nil)))
  (is (= \A (xor-char [])))
  (is (= \A (xor-char [\A \B \C])))
  (is (= \C (xor-char [\A \B])))
  (is (= \Z (xor-char [\a])))
  (is (= \Q (xor-char [\x \y \z]))))

(run-test test-variation)
