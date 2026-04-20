(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "three two one" (reverse-sections "one two three")))
  (is (= " c b  a  " (reverse-sections "  a  b c ")))
  (is (= "" (reverse-sections nil))))

(run-test test-variation)
