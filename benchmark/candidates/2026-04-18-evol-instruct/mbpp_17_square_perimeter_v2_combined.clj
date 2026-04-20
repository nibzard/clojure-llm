(defn square-diagonals
  "Return a lazy sequence of the first n diagonal lengths of squares, starting from side length 1.

  Each diagonal length is side-length * sqrt(2).

  Examples:
  (square-diagonals 3) => (1.4142135623730951 2.8284271247461903 4.242640687119286)
  (square-diagonals 0) => ()"
  [n]
  (map #(* % (Math/sqrt 2.0)) (range 1 (inc n))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1.4142135623730951 2.8284271247461903 4.242640687119286]
         (vec (square-diagonals 3)))))

(run-test test-variation)
