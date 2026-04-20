(defn nth-octagonal
  "Return the nth octagonal number as a lazy sequence element.
  
  Supports any non-negative integer n.
  
  Examples:
  (nth-octagonal 1)  ;=> 1
  (nth-octagonal 4)  ;=> 28
  (nth-octagonal 10) ;=> 145"
  [n]
  (let [octagonal-seq (map #(+ (* 3 % %) (* -2 %)) (iterate inc 1))]
    (nth octagonal-seq (dec n))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (nth-octagonal 1)))
  (is (= 28 (nth-octagonal 4)))
  (is (= 145 (nth-octagonal 10))))

(run-test test-variation)
