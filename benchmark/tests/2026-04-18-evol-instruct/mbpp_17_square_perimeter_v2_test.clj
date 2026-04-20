(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1.4142135623730951 2.8284271247461903 4.242640687119286]
         (vec (square-diagonals 3)))))

(run-test test-variation)
