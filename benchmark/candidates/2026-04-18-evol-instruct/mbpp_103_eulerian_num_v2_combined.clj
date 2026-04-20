(defn eulerian_triangle
  "Return the first n rows of the Eulerian triangle as a vector of vectors.

  The first row is [1]. Each next row k (0-based) contains the Eulerian numbers
  a(k, m) for m = 0..k.

  Examples:
  (eulerian_triangle 1) => [[1]]
  (eulerian_triangle 4) => [[1] [1 0] [1 1 0] [1 4 1 0]]"
  [n]
  (letfn [(next-row [row k]
            (vec
             (for [m (range (inc k))]
               (cond
                 (= m 0) 1
                 (= m k) 0
                 :else (+ (* (inc m) (nth row m))
                          (* (- k m) (nth row (dec m))))))))]
    (vec
     (take n
           (iterate (fn [[row k]]
                      [(next-row row k) (inc k)])
                    [[1] 1])))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1]] (eulerian_triangle 1)))
  (is (= [[1] [1 0] [1 1 0] [1 4 1 0]] (eulerian_triangle 4))))

(run-test test-variation)
