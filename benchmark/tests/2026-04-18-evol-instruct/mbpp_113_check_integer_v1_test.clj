(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (parse-integer? "42")))
  (is (= true (parse-integer? " -17 ")))
  (is (= true (parse-integer? "+0")))
  (is (= false (parse-integer? "3.14")))
  (is (= false (parse-integer? nil)))
  (is (= false (parse-integer? ""))))

(run-test test-variation)
