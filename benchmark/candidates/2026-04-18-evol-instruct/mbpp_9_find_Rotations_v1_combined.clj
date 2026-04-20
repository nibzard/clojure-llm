(defn min-prefix-rotations
  "Return the minimum number of left rotations greater than 0 needed to make a vector equal to itself.
If no such rotation exists, return nil.

Examples:
(min-prefix-rotations [1 2 3 1 2 3]) => 3
(min-prefix-rotations [:a :b :a :b]) => 2
(min-prefix-rotations [1 2 3 4]) => nil"
  [v]
  (let [n (count v)]
    (when (pos? n)
      (first
       (for [k (range 1 n)
             :when (= v (vec (concat (subvec (vec v) k) (subvec (vec v) 0 k))))]
         k)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (min-prefix-rotations [1 2 3 1 2 3])))
  (is (= 2 (min-prefix-rotations [:a :b :a :b])))
  (is (= nil (min-prefix-rotations [1 2 3 4]))))

(run-test test-variation)
