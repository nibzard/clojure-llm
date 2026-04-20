(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 3 5) (split-odds [1 2 3 4 5])))
  (is (= '(7 9) (split-odds [nil 7 8 9])))
  (is (= '(1 3 5 7) (take 4 (split-odds (range))))))

(run-test test-variation)
