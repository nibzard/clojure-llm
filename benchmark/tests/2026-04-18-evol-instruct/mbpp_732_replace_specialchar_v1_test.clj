(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a:b:c:d" (redact-specialchar "a b,c.d")))
  (is (= "" (redact-specialchar nil))))

(run-test test-variation)
