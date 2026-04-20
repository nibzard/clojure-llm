(defn subvec?
  "Return true if vector `v` contains vector `pattern` as a contiguous subsequence.

  Examples:
  (subvec? [1 2 3 4] [2 3]) => true
  (subvec? [1 2 3 4] []) => true
  (subvec? [] [1]) => false

  The function should work with nil inputs by treating nil as an empty vector."
  [v pattern]
  (let [v (vec (or v []))
        pattern (vec (or pattern []))
        n (count pattern)]
    (cond
      (zero? n) true
      (> n (count v)) false
      :else (boolean
             (some #(= pattern (subvec v % (+ % n)))
                   (range (inc (- (count v) n))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (subvec? [1 2 3 4] [2 3]))))

(run-test test-variation)
