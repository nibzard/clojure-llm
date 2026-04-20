(defn max-zero-one-diff
  "Return the maximum difference between the number of 0s and 1s in any contiguous subsequence of a vector of integers.

  Treat 0 as +1 and 1 as -1, and return the best possible sum over all non-empty contiguous sub-vectors.

  Examples:
  (max-zero-one-diff [0 1 0 0 1]) ;=> 2
  (max-zero-one-diff [1 1 1])     ;=> -1
  (max-zero-one-diff [])          ;=> nil"
  [nums]
  (when (seq nums)
    (let [vals (map #(if (zero? %) 1 -1) nums)]
      (second
       (reduce (fn [[best-ending best-so-far] x]
                 (let [ending (max x (+ best-ending x))]
                   [ending (max best-so-far ending)]))
               [(first vals) (first vals)]
               (rest vals))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 2 (max-zero-one-diff [0 1 0 0 1])))
  (is (= -1 (max-zero-one-diff [1 1 1])))
  (is (= nil (max-zero-one-diff []))))

(run-test test-variation)
