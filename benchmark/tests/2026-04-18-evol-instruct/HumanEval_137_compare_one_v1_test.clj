(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 4] (merge-odd-indexed [1 2 3 4 5])))
  (is (= ["b" "d"] (merge-odd-indexed ["a" "b" "c" "d"])))
  (is (= [] (merge-odd-indexed nil))))

(run-test test-variation)
