(defn sort-keys-by-value
  "Given a map of keywords to numeric values, return a vector of the map's keys
  sorted from smallest value to largest value.

  If two keys have the same value, sort those keys by their name in ascending
  lexicographic order.

  Examples:
  (sort-keys-by-value {:c 3, :a 1, :b 2})
  => [:a :b :c]

  (sort-keys-by-value {:z 2, :x 2, :a 1})
  => [:a :x :z]"
  [m]
  (->> m
       (sort-by (fn [[k v]] [v (name k)]))
       (mapv key)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [:a :b :c] (sort-keys-by-value {:c 3, :a 1, :b 2}))))

(run-test test-variation)
