(defn remove-nth
  "Returns a vector with the nth element removed.

  Indices are zero-based. If n is out of range, returns the original vector.
  Works with vectors and nil input.

  Examples:
  (remove-nth 1 [1 2 3 4]) ;=> [1 3 4]
  (remove-nth 0 [:a :b :c]) ;=> [:b :c]
  (remove-nth 5 [1 2])     ;=> [1 2]"
  [v n]
  (let [v (or v [])
        cnt (count v)]
    (if (and (<= 0 n) (< n cnt))
      (vec (concat (subvec (vec v) 0 n)
                   (subvec (vec v) (inc n) cnt)))
      (vec v))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 4] (remove-nth [1 2 3 4] 1))))

(run-test test-variation)
