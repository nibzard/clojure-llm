(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-length-word? "cat")))
  (is (= false (odd-length-word? "four")))
  (is (= false (odd-length-word? nil))))

(run-test test-variation)
