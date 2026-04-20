(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '("to" "four" "six")
         (filter-even-length-words ["a" "to" nil "four" "five" "six"]))))

(run-test test-variation)
