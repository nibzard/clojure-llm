(defn left-insertion-once
  "Return the leftmost insertion index for x in a sorted sequence that may be infinite.
Works on any seqable collection and must stop as soon as the answer is known.

Examples:
(left-insertion-once [1 2 2 4 5] 2) => 1
(left-insertion-once [1 3 5] 4) => 2
(left-insertion-once (range) 100) => 100"
  [xs x]
  (loop [s (seq xs)
         idx 0]
    (if (nil? s)
      idx
      (let [v (first s)]
        (if (< v x)
          (recur (next s) (inc idx))
          idx)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (left-insertion-once [1 2 2 4 5] 2)))
  (is (= 2 (left-insertion-once [1 3 5] 4)))
  (is (= 100 (left-insertion-once (range) 100))))

(run-test test-variation)
