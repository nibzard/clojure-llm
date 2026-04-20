(defn left-insertion-index
  "Return the leftmost insertion index for x in a sorted collection a, using binary search.
Examples:
  (left-insertion-index [1 2 2 4 5] 2) => 1
  (left-insertion-index [1 2 2 4 5] 3) => 3
  (left-insertion-index [] 10) => 0
Works with any sequential collection that supports nth and count."
  [a x]
  (loop [lo 0
         hi (count a)]
    (if (< lo hi)
      (let [mid (quot (+ lo hi) 2)]
        (if (< (nth a mid) x)
          (recur (inc mid) hi)
          (recur lo mid)))
      lo)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (left-insertion-index [1 2 2 4 5] 2)))
  (is (= 3 (left-insertion-index [1 2 2 4 5] 3)))
  (is (= 0 (left-insertion-index [] 10))))

(run-test test-variation)
