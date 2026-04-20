(defn top-distinct
  "Given a vector arr of integers and a non-negative integer k, return a sorted vector
  containing the largest k DISTINCT numbers from arr.

  If arr has fewer than k distinct values, return all distinct values sorted in ascending order.
  If k is 0, return an empty vector.
  Treat nil as an empty vector.

  Examples:
  >>> (top-distinct [3 1 3 2 2 5] 3)
  [2 3 5]
  >>> (top-distinct [4 4 4] 2)
  [4]
  >>> (top-distinct nil 5)
  []

  Notes:
      1. The input may be nil.
      2. The result must be a vector, sorted in ascending order.
      3. The function should work correctly for any integer values."
  [arr k]
  (->> (or arr [])
       distinct
       sort
       reverse
       (take k)
       sort
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [2 3 5] (top-distinct [3 1 3 2 2 5] 3)))
  (is (= [4] (top-distinct [4 4 4] 2)))
  (is (= [] (top-distinct nil 5)))
  (is (= [] (top-distinct [9 8 7] 0))))

(run-test test-variation)
