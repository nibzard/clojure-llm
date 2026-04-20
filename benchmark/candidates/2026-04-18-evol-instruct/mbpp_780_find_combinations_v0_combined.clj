(defn vector-sum-subsets
  "Return all subsets of the input vector whose elements sum to the target.
  Preserve the original order of elements within each subset.

  Examples:
  (vector-sum-subsets [1 2 3 4] 5) => [[1 4] [2 3]]
  (vector-sum-subsets [2 2 2] 4) => [[2 2] [2 2] [2 2]]"
  [v target]
  (letfn [(step [xs]
            (lazy-seq
              (if-let [s (seq xs)]
                (let [x (first s)
                      more (step (rest s))
                      with-x (map #(cons x %) more)]
                  (concat
                    (filter #(= target (reduce + %)) (list (list x)))
                    (filter #(= target (reduce + %)) more)
                    (filter #(= target (reduce + %)) with-x)))
                '())))]
    (mapv vec (step v))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 4] [2 3]] (vector-sum-subsets [1 2 3 4] 5))))

(run-test test-variation)
