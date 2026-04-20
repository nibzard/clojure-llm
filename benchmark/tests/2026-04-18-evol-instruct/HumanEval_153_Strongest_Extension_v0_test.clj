(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "bab" (longest-palindrome-substring "babad")))
  (is (= "bb" (longest-palindrome-substring "cbbd")))
  (is (= "a" (longest-palindrome-substring "a")))
  (is (= "" (longest-palindrome-substring "")))
  (is (= "anana" (longest-palindrome-substring "banana"))))

(run-test test-variation)
