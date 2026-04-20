(defn digit-distance-coll
  "Return the sum of absolute differences between corresponding digits in two collections of digits.

  The collections may be vectors or sequences of integers. If one collection is shorter,
  treat missing digits as 0.

  Examples:
  (digit-distance-coll [1 2 3] [4 5 6]) ;=> 9
  (digit-distance-coll [9 1] [2 8 7])   ;=> 11
  (digit-distance-coll nil [1 2 3])     ;=> 6"
  [xs ys]
  (reduce
    (fn [acc [a b]]
      (+ acc (Math/abs ^long (- (long (or a 0)) (long (or b 0))))))
    0
    (map vector
         (concat (or xs []) (repeat 0))
         (concat (or ys []) (repeat 0)))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 9 (digit-distance-coll [1 2 3] [4 5 6])))
  (is (= 11 (digit-distance-coll [9 1] [2 8 7])))
  (is (= 6 (digit-distance-coll nil [1 2 3]))))

(run-test test-variation)
