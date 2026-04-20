(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "" (longest-palindromic-prefix nil)))
  (is (= "tacat" (longest-palindromic-prefix "cat")))
  (is (= "atacata" (longest-palindromic-prefix "cata")))
  (is (= "racecar" (longest-palindromic-prefix "racecar"))))

(run-test test-variation)
