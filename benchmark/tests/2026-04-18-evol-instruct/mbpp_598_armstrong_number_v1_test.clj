(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindrome-number? 121)))
  (is (= false (palindrome-number? 123)))
  (is (= true (palindrome-number? 0)))
  (is (= false (palindrome-number? -121))))

(run-test test-variation)
