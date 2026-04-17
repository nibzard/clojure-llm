(require '[clojure.test :refer [deftest is run-test]])

(def candidate separate_paren_groups)

(deftest test-humaneval

  (is (= (candidate "(()()) ((())) () ((())()())") ["(()())" "((()))" "()" "((())()())"]))
  (is (= (candidate "() (()) ((())) (((())))") ["()" "(())" "((()))" "(((())))"]))
  (is (= (candidate "(()(())((())))") ["(()(())((())))"]))
  (is (= (candidate "( ) (( )) (( )( ))") ["()" "(())" "(()())"]))
)

(run-test test-humaneval)