(defn collapse-runs
  "Return a vector of [value run-length] pairs for each consecutive run in nums.

  Examples:
  (collapse-runs [1 1 1 2 2 3]) ;=> [[1 3] [2 2] [3 1]]
  (collapse-runs [])            ;=> []
  (collapse-runs nil)           ;=> []"
  [nums]
  (if-let [s (seq nums)]
    (loop [xs (rest s)
           curr (first s)
           n 1
           acc []]
      (if-let [x (first xs)]
        (if (= x curr)
          (recur (rest xs) curr (inc n) acc)
          (recur (rest xs) x 1 (conj acc [curr n])))
        (conj acc [curr n])))
    []))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[1 3] [2 2] [3 1]] (collapse-runs [1 1 1 2 2 3])))
  (is (= [] (collapse-runs [])))
  (is (= [] (collapse-runs nil))))

(run-test test-variation)
