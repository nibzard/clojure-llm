(defn palindrome-number?
  "Return true if n is a palindrome in base 10, otherwise false.

  Examples:
  (palindrome-number? 121) => true
  (palindrome-number? 123) => false
  (palindrome-number? 0)   => true
  (palindrome-number? -121) => false"
  [n]
  (let [s (str n)]
    (and (not (neg? n))
         (= s (apply str (reverse s))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindrome-number? 121)))
  (is (= false (palindrome-number? 123)))
  (is (= true (palindrome-number? 0)))
  (is (= false (palindrome-number? -121))))

(run-test test-variation)
