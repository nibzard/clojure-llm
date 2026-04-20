(defn compress-runs
  "Return a lazy sequence of [value run-length] pairs for each consecutive run in `coll`.

  Examples:
  (compress-runs [1 1 1 2 2 3])
  ;; => ([1 3] [2 2] [3 1])

  (compress-runs nil)
  ;; => ()

  Works with any sequential collection."
  [coll]
  (lazy-seq
    (when-let [s (seq coll)]
      (let [x (first s)
            n (count (take-while #(= x %) s))
            rest-coll (drop n s)]
        (cons [x n] (compress-runs rest-coll))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3] [2 2] [3 1]] (compress-runs [1 1 1 2 2 3]))))

(run-test test-variation)
