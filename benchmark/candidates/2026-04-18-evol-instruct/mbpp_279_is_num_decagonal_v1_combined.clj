(defn decagonal-seq
  "Return a lazy sequence of decagonal numbers.

  A decagonal number is given by n * (4n - 3).

  Examples:
  (take 5 (decagonal-seq)) ;=> (1 7 18 34 55)
  (nth (decagonal-seq) 9)  ;=> 325"
  []
  (map (fn [n] (* n (- (* 4 n) 3))) (iterate inc 1)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 7 18 34 55) (take 5 (decagonal-seq))))
  (is (= 325 (nth (decagonal-seq) 9))))

(run-test test-variation)
