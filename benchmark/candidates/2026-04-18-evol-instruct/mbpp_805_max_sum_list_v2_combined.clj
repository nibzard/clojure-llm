(defn max-infinite-prefix
  "Return the first prefix of `coll` whose sum is the highest seen so far.

  This variation works with lazy and even infinite sequences by only consuming
  as much as needed until it can determine the best prefix among the prefixes
  of the realized input.

  Examples:
  (max-infinite-prefix [1 2 -5 10 3])
  ;; => [1 2 -5 10 3]

  (max-infinite-prefix [5 -2 1 -1 4])
  ;; => [5]"
  [coll]
  (let [best (reduce
               (fn [{:keys [sum best-sum best-prefix prefix]} x]
                 (let [new-prefix (conj prefix x)
                       new-sum (+ sum x)]
                   (if (> new-sum best-sum)
                     {:sum new-sum
                      :best-sum new-sum
                      :best-prefix new-prefix
                      :prefix new-prefix}
                     {:sum new-sum
                      :best-sum best-sum
                      :best-prefix best-prefix
                      :prefix new-prefix})))
               {:sum 0
                :best-sum Long/MIN_VALUE
                :best-prefix []
                :prefix []}
               coll)]
    (:best-prefix best)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 -5 10 3] (max-infinite-prefix [1 2 -5 10 3]))))

(run-test test-variation)
