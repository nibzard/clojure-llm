(defn sort-counter-by-count
  "Return a vector of [key count] pairs from the input map, sorted by descending count.
  If counts are tied, sort by key in ascending order.

  Examples:
  (sort-counter-by-count {:a 3, :b 1, :c 3})
  => [[:a 3] [:c 3] [:b 1]]

  (sort-counter-by-count nil)
  => []"
  [m]
  (->> (or m {})
       (sort-by (fn [[k v]] [(- v) k]))
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [[:a 3] [:c 3] [:b 1]]
         (sort-counter-by-count {:a 3 :b 1 :c 3})))
  (is (= [] (sort-counter-by-count nil))))

(run-test test-variation)
