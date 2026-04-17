(require '[clojure.test :refer [deftest is run-test]])

(def candidate match_parens)

(deftest test-humaneval

  (is (= (candidate ["()(" ")"]) "Yes"))
  (is (= (candidate [")" ")"]) "No"))
  (is (= (candidate ["(()(())" "())())"]) "No"))
  (is (= (candidate [")())" "(()()("]) "Yes"))
  (is (= (candidate ["(())))" "(()())(("]) "Yes"))
  (is (= (candidate ["()" "())"]) "No"))
  (is (= (candidate ["(()(" "()))()"]) "Yes"))
  (is (= (candidate ["((((" "((())"]) "No"))
  (is (= (candidate [")(()" "(()("]) "No"))
  (is (= (candidate [")(" ")("]) "No"))
  (is (= (candidate ["(" ")"]) "Yes"))
  (is (= (candidate [")" "("]) "Yes"))
)

(run-test test-humaneval)