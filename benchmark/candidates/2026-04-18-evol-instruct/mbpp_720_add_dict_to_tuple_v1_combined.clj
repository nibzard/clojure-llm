(defn merge-vectors-by-index
  "Merge two vectors by index, using a combining function for each pair.
   If one vector is longer, append the remaining elements unchanged.
   Examples:
   (merge-vectors-by-index [1 2 3] [10 20] +) => [11 22 3]
   (merge-vectors-by-index [1] [10 20 30] str) => [\"110\" 20 30]"
  [v1 v2 combine-fn]
  (vec
   (map (fn [a b]
          (cond
            (and (some? a) (some? b)) (combine-fn a b)
            (some? a) a
            :else b))
        (concat v1 (repeat nil))
        (concat v2 (repeat nil)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [11 22 3] (merge-vectors-by-index [1 2 3] [10 20] +)))
  (is (= ["110" 20 30] (merge-vectors-by-index [1] [10 20 30] str))))

(run-test test-variation)
