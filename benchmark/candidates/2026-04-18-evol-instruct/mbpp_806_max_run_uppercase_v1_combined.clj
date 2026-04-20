(defn max-run
  "Return the length of the longest contiguous run of truthy values in a sequence.

  Examples:
  (max-run [false true true false true]) => 2
  (max-run [1 2 3]) => 3
  (max-run []) => 0
  (max-run nil) => 0"
  [xs]
  (loop [s (seq xs)
         current 0
         best 0]
    (if (nil? s)
      best
      (let [x (first s)
            current' (if x (inc current) 0)
            best' (max best current')]
        (recur (next s) current' best')))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-run [false true true false true]))))

(run-test test-variation)
