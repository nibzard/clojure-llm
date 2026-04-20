(defn odd-position-sums
  "Return true if every odd index in the vector is equal to the sum of the two neighboring even indices.

  Examples:
  (odd-position-sums [1 2 1])    ;=> true
  (odd-position-sums [2 3 1 4 2]) ;=> true
  (odd-position-sums [1 5 2])    ;=> false

  If the vector has fewer than 3 elements, return false."
  [nums]
  (and (vector? nums)
       (>= (count nums) 3)
       (every? true?
               (for [i (range 1 (dec (count nums)) 2)]
                 (= (nth nums i)
                    (+ (nth nums (dec i))
                       (nth nums (inc i))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (odd-position-sums [1 2 1])))
  (is (= true (odd-position-sums [2 3 1 4 2])))
  (is (= false (odd-position-sums [1 5 2]))))

(run-test test-variation)
