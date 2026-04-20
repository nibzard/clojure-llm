(defn problem-palindrome-detector [a]
  (= (seq a) (reverse a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-palindrome-detector
  (is (= (false? (problem-palindrome-detector '(1 2 3 4 5)))))
  (is (= (true? (problem-palindrome-detector "racecar"))))
  (is (= (true? (problem-palindrome-detector [:foo :bar :foo]))))
  (is (= (true? (problem-palindrome-detector '(1 1 3 3 1 1)))))
  (is (= (false? (problem-palindrome-detector '(:a :b :c))))))

(run-tests)
