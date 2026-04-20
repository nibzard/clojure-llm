(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:area 9, :perimeter 12} (square_area-or-perimeter 3))))

(run-test test-variation)
