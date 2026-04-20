(defn rescale-map-values
  "Given a map of keyword keys to numeric values, linearly rescale the values so the smallest becomes 0.0 and the largest becomes 1.0.

   If all values are equal, return a map with all values set to 0.0.

   Examples:
   (rescale-map-values {:a 10 :b 20 :c 30})
   => {:a 0.0, :b 0.5, :c 1.0}

   (rescale-map-values {:x 5 :y 5})
   => {:x 0.0, :y 0.0}"
  [m]
  (let [vals (vals m)
        mn (apply min vals)
        mx (apply max vals)
        range (- mx mn)]
    (if (zero? range)
      (into {} (map (fn [[k _]] [k 0.0]) m))
      (into {} (map (fn [[k v]] [k (/ (- v mn) range)]) m)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= {:a 0.0, :b 0.5, :c 1.0}
         (rescale-map-values {:a 10 :b 20 :c 30}))))

(run-test test-variation)
