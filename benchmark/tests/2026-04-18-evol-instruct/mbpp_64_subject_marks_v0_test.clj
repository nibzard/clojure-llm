(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [["b" 5] ["a" 2] ["c" 1]]
         (sort-by-second-desc [["a" 2] ["b" 5] ["c" 1]]))))

(run-test test-variation)
