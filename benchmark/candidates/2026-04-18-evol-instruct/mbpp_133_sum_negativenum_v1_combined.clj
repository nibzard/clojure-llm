(defn sum_negative-stream
  "Return the sum of all negative numbers in `nums`.

  Works with any seqable collection, including lazy and infinite sequences.

  Examples:
  (sum_negative-stream [1 -2 3 -4]) ;=> -6
  (sum_negative-stream nil)         ;=> 0
  (sum_negative-stream (range))     ;=> 0"
  [nums]
  (transduce (filter neg?) + 0 (or nums [])))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= -6 (sum_negative-stream [1 -2 3 -4])))
  (is (= 0 (sum_negative-stream nil)))
  (is (= 0 (sum_negative-stream (range 5)))))

(run-test test-variation)
