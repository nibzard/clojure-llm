(defn closest_num
  "Return the closest smaller Fibonacci number than n.

  Examples:
  (closest_num 1)  => nil
  (closest_num 2)  => 1
  (closest_num 10) => 8

  If n is nil or not positive, return nil."
  [N]
  (when (and (number? N) (pos? N))
    (loop [a 0
           b 1]
      (if (< b N)
        (recur b (+ a b))
        a))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= nil (closest_num 1)))
  (is (= 1 (closest_num 2)))
  (is (= 8 (closest_num 10))))

(run-test test-variation)
