(defn remove-kth-from-vector
  "Return a new vector with the element at zero-based index k removed.

  If k is out of bounds, return the original vector unchanged.

  Examples:
  (remove-kth-from-vector [1 2 3 4] 2) ;=> [1 2 4]
  (remove-kth-from-vector [:a :b :c] 0) ;=> [:b :c]
  (remove-kth-from-vector [] 0) ;=> []"
  [v k]
  (if (and (vector? v) (<= 0 k) (< k (count v)))
    (vec (concat (subvec v 0 k) (subvec v (inc k))))
    v))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 2 4] (remove-kth-from-vector [1 2 3 4] 2)))
  (is (= [:b :c] (remove-kth-from-vector [:a :b :c] 0)))
  (is (= [] (remove-kth-from-vector [] 0)))
  (is (= [1 2 3] (remove-kth-from-vector [1 2 3] 10))))

(run-test test-variation)
