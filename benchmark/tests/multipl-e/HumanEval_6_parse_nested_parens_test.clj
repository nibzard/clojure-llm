(require '[clojure.test :refer [deftest is run-test]])

(def candidate parse_nested_parens)

(deftest test-humaneval

  (is (= (candidate "(()()) ((())) () ((())()())") [2 3 1 3]))
  (is (= (candidate "() (()) ((())) (((())))") [1 2 3 4]))
  (is (= (candidate "(()(())((())))") [4]))
)

(run-test test-humaneval)