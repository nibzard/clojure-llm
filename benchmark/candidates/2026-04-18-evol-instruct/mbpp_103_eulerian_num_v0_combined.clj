(defn eulerian-row
  "Return the nth row of the Eulerian numbers as a vector.

  The result is a vector of length n, where the i-th entry is a(n, i).
  For example:
  (eulerian-row 0) => [1]
  (eulerian-row 1) => [1]
  (eulerian-row 3) => [1 4 1]"
  [n]
  (letfn [(next-row [row k]
            (vec
             (for [m (range (inc k))]
               (let [a1 (if (pos? m) (* (- k m) (nth row (dec m))) 0)
                     a2 (if (< m k) (* (inc m) (nth row m)) 0)]
                 (+ a1 a2)))))]
    (loop [k 0
           row [1]]
      (if (= k n)
        row
        (recur (inc k) (next-row row k))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1] (eulerian-row 0)))
  (is (= [1] (eulerian-row 1)))
  (is (= [1 4 1] (eulerian-row 3))))

(run-test test-variation)
