(defn vector-subset-sum
  "Return the first subset of the given vector whose values sum to target.

  The input is a vector of integers. Search subsets in increasing length order,
  and within the same length, preserve the original left-to-right order.

  Return a vector containing the matching subset, or nil if no subset exists.

  Examples:
  (vector-subset-sum [1 2 3 4] 5) => [1 4]
  (vector-subset-sum [2 4 6] 5) => nil
  (vector-subset-sum [] 0) => []"
  [nums target]
  (let [n (count nums)]
    (loop [k 0]
      (if (> k n)
        nil
        (if-let [result
                 (first
                  (for [idxs (if (zero? k)
                               [[]]
                               (letfn [(choose [start left]
                                         (lazy-seq
                                          (if (zero? left)
                                            (list [])
                                            (when (< start n)
                                              (concat
                                               (map #(cons start %) (choose (inc start) (dec left)))
                                               (choose (inc start) left))))))]
                                 (choose 0 k)))
                        :let [subset (mapv nums idxs)]
                        :when (= (reduce + 0 subset) target)]
                    subset)]
          result
          (recur (inc k)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 4] (vector-subset-sum [1 2 3 4] 5)))
  (is (= nil (vector-subset-sum [2 4 6] 5)))
  (is (= [] (vector-subset-sum [] 0))))

(run-test test-variation)
