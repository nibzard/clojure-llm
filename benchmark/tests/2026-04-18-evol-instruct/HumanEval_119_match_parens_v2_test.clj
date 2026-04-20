(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (merge-valid-parens ["()(" ")"])))
  (is (= false (merge-valid-parens [")" ")"])))
  (is (= true (merge-valid-parens ["((" "))"])))

(run-test test-variation)
