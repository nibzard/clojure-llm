(defn triples_sum_to_zero_sorted
  "triples_sum_to_zero_sorted takes a sorted vector of integers as input.
  It returns true if there are three distinct elements in the vector whose
  sum is zero, and false otherwise.

  The input vector is sorted in nondecreasing order and may contain duplicates.

  >>> (triples_sum_to_zero_sorted [1 3 5 0])
  false
  >>> (triples_sum_to_zero_sorted [-4 -1 -1 0 1 2])
  true
  >>> (triples_sum_to_zero_sorted [0 0 0])
  true
  >>> (triples_sum_to_zero_sorted [-2 1 1])
  true
  >>> (triples_sum_to_zero_sorted [1 2 4 8])
  false"
  [v]
  (let [n (count v)]
    (boolean
      (some true?
            (for [i (range (dec (dec n)))
                  j (range (inc i) (dec n))
                  k (range (inc j) n)]
              (zero? (+ (nth v i) (nth v j) (nth v k))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= false (triples_sum_to_zero_sorted [1 3 5 0])))
  (is (= true (triples_sum_to_zero_sorted [-4 -1 -1 0 1 2])))
  (is (= true (triples_sum_to_zero_sorted [0 0 0])))
  (is (= true (triples_sum_to_zero_sorted [-2 1 1])))
  (is (= false (triples_sum_to_zero_sorted [1 2 4 8]))))

(run-test test-variation)
