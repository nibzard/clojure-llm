(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2] (palindromic-even-odd-count 3)))
  (is (= [4 6] (palindromic-even-odd-count 12)))
  (is (= [5 9] (palindromic-even-odd-count 20))))

(run-test test-variation)
