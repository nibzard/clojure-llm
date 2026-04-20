(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (palindromic-length? [1 2 3 4 5])))
  (is (= false (palindromic-length? '(a b c d))))
  (is (= true (palindromic-length? (range 11))))
  (is (= false (palindromic-length? nil))))

(run-test test-variation)
