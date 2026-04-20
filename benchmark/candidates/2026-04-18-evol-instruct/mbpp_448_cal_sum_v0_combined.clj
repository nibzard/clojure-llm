(defn cal_sum_even
  "Return the sum of the first n even Perrin numbers.

  The Perrin sequence starts:
  3, 0, 2, 3, 2, 5, 5, 7, 10, ...

  Examples:
  (cal_sum_even 1) => 0
  (cal_sum_even 3) => 2
  (cal_sum_even 5) => 4"
  [n]
  (letfn [(perrin-seq [a b c]
            (lazy-seq
              (cons a (perrin-seq b c (+ a b)))))]
    (->> (perrin-seq 3 0 2)
         (filter even?)
         (take n)
         (reduce + 0))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 0 (cal_sum_even 1)))
  (is (= 2 (cal_sum_even 3)))
  (is (= 4 (cal_sum_even 5))))

(run-test test-variation)
