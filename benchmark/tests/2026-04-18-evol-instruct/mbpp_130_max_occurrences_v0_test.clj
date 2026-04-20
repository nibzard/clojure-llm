(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "a" (most-frequent-prefix "banana")))
  (is (= "a" (most-frequent-prefix "aaaa")))
  (is (= nil (most-frequent-prefix nil))))

(run-test test-variation)
