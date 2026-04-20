(defn lucas-seq
  "Return the first n Lucas numbers as a lazy sequence.

  Examples:
  (lucas-seq 0) => ()
  (lucas-seq 1) => (2)
  (lucas-seq 5) => (2 1 3 4 7)"
  [n]
  (take n (map first (iterate (fn [[a b]] [b (+ a b)]) [2 1]))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '() (lucas-seq 0)))
  (is (= '(2) (lucas-seq 1)))
  (is (= '(2 1 3 4 7) (lucas-seq 5))))

(run-test test-variation)
