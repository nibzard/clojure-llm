(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [["fig" "pear" "apple"] ["a" "cc" "bbb"]]
         (sort-by-length-of-words [["pear" "fig" "apple"] ["a" "bbb" "cc"]]))))

(run-test test-variation)
