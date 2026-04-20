(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a:b:c:d" (replace_specialchar "a b,c.d")))
  (is (= nil (replace_specialchar nil))))

(run-test test-variation)
