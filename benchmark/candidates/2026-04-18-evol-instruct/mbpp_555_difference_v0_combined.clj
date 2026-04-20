(defn difference-of-powers
  "Return the difference between the sum of squares of the first n even numbers and the sum of the first n odd numbers.
  If n is nil or non-positive, return 0.

  Examples:
  (difference-of-powers 1) ;=> 3   ; 2^2 - 1
  (difference-of-powers 3) ;=> 29  ; (2^2+4^2+6^2) - (1+3+5)
  (difference-of-powers nil) ;=> 0"
  [n]
  (if (and n (pos? n))
    (- (reduce + (map #(* % %) (take n (iterate #(+ % 2) 2))))
       (reduce + (take n (iterate #(+ % 2) 1))))
    0))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 3 (difference-of-powers 1)))
  (is (= 29 (difference-of-powers 3)))
  (is (= 0 (difference-of-powers nil))))

(run-test test-variation)
