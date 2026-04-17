(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_parenthesis)

(deftest test-humaneval

  (is (= (candidate ["python (chrome)"]) "python"))
  (is (= (candidate ["string(.abc)"]) "string"))
  (is (= (candidate ["alpha(num)"]) "alpha"))
)

(run-test test-humaneval)