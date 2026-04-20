(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindromic-prefix? [1 2 1])))
  (is (= true (palindromic-prefix? [1 nil 1])))
  (is (= true (palindromic-prefix? [])))
  (is (= false (palindromic-prefix? [1 2 3]))))

(run-test test-variation)
