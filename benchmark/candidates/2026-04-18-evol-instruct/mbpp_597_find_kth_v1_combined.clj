(defn kth-merge
  "Return the kth smallest element from two sorted sequences without fully realizing them.
  k is 1-based. Works with vectors, lists, and infinite sorted sequences.

  Examples:
  (kth-merge [1 3 5] [2 4 6] 4) => 4
  (kth-merge [1 2] [3 4 5] 5) => 5
  (kth-merge (range) [10 20 30] 3) => 2"
  [xs ys k]
  (loop [a (seq xs)
         b (seq ys)
         n k]
    (cond
      (zero? n) nil
      (nil? a) (nth b (dec n))
      (nil? b) (nth a (dec n))
      (<= (first a) (first b)) (if (= n 1)
                                 (first a)
                                 (recur (rest a) b (dec n)))
      :else (if (= n 1)
              (first b)
              (recur a (rest b) (dec n))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 4 (kth-merge [1 3 5] [2 4 6] 4))))

(run-test test-variation)
