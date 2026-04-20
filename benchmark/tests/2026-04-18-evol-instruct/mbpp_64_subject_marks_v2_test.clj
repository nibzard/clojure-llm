(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [["science" 92] ["math" 85] ["art" 78]]
         (sort-by-second-desc [["math" 85] ["science" 92] ["art" 78]]))))

(run-test test-variation)
