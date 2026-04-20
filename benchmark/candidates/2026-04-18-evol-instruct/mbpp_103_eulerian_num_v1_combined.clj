(defn eulerian-num
  "Return the Eulerian number A(n, m), the number of permutations of 1..n with exactly m ascents.

  The function must accept a sequence of [n m] query pairs and return a lazy sequence of results.

  Examples:
  (eulerian-num [[3 0] [3 1] [4 1]])
  => (1 4 11)

  Invalid queries such as negative n or m, or m >= n, should yield 0."
  [queries]
  (letfn [(eulerian [n m]
            (cond
              (neg? n) 0
              (neg? m) 0
              (>= m n) 0
              (and (= n 0) (= m 0)) 1
              (or (= n 0) (= m 0)) 1
              :else
              (let [num1 (* (inc (- n m)) (eulerian (dec n) (dec m)))
                    num2 (* (inc m) (eulerian (dec n) m))]
                (+ num1 num2))))]
    (map (fn [[n m]] (eulerian n m)) queries)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= '(1 4 11) (eulerian-num [[3 0] [3 1] [4 1]]))))

(run-test test-variation)
