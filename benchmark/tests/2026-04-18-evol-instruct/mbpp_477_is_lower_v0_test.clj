(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindromic? "A man, a plan, a canal: Panama")))
  (is (= false (palindromic? "Hello, world!")))
  (is (= false (palindromic? nil))))

(run-test test-variation)
