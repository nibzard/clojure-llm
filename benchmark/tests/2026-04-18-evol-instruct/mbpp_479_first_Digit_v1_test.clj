(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \7 (first-digit-in-string "abc7def")))
  (is (= nil (first-digit-in-string "no digits")))
  (is (= nil (first-digit-in-string nil))))

(run-test test-variation)
