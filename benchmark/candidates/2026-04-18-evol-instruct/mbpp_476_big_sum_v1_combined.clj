(defn sum-extremes
  "Return the sum of the smallest and largest numeric values in a possibly nested
  collection.

  Nil values are ignored. Non-numeric values are ignored.

  Examples:
  (sum-extremes [3 1 9 4])
  ;; => 10

  (sum-extremes [nil [7 2] #{5 1} 8])
  ;; => 10

  (sum-extremes [])
  ;; => nil"
  [coll]
  (let [nums (filter number? (tree-seq coll? seq coll))]
    (when (seq nums)
      (+ (apply min nums) (apply max nums)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 10 (sum-extremes [3 1 9 4])))
  (is (= 10 (sum-extremes [nil [7 2] #{5 1} 8])))
  (is (= nil (sum-extremes []))))

(run-test test-variation)
