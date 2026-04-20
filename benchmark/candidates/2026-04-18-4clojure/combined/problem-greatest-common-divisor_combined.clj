(defn problem-greatest-common-divisor [a b]
  (loop [x (Math/abs (long a))
         y (Math/abs (long b))]
    (if (zero? y)
      x
      (recur y (mod x y)))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-greatest-common-divisor
  (is (= (= (problem-greatest-common-divisor 2 4) 2)))
  (is (= (= (problem-greatest-common-divisor 10 5) 5)))
  (is (= (= (problem-greatest-common-divisor 5 7) 1)))
  (is (= (= (problem-greatest-common-divisor 1023 858) 33))))

(run-tests)
