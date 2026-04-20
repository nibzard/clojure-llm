(defn kth-distinct
  "Return the kth distinct number from the input collection, counting only values that appear exactly once.

  The function should preserve the original order of the collection when determining distinct values.

  Examples:
  (kth-distinct [1 2 2 3 4 4 5] 1) => 1
  (kth-distinct [1 2 2 3 4 4 5] 2) => 3
  (kth-distinct [1 2 2 3 4 4 5] 3) => 5
  (kth-distinct [1 1 1] 1) => nil"
  [coll k]
  (let [freqs (frequencies coll)]
    (nth (filter #(= 1 (get freqs %)) coll) (dec k) nil)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 1 (kth-distinct [1 2 2 3 4 4 5] 1)))
  (is (= 3 (kth-distinct [1 2 2 3 4 4 5] 2)))
  (is (= 5 (kth-distinct [1 2 2 3 4 4 5] 3)))
  (is (= nil (kth-distinct [1 1 1] 1))))

(run-test test-variation)
