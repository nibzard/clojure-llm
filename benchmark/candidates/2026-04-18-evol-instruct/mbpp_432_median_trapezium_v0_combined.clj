(defn median-triangle
  "Return the median of a triangle formed by the three side lengths.
   Accepts any collection of 3 numeric values, including nil-safe inputs.
   Examples:
   (median-triangle [3 4 5]) => 4
   (median-triangle '(10 1 7)) => 7"
  [sides]
  (let [nums (->> sides
                  (remove nil?)
                  sort
                  vec)]
    (when (= 3 (count nums))
      (nth nums 1))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (median-triangle [3 4 5]))))

(run-test test-variation)
