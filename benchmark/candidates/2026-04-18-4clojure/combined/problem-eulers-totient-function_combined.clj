(defn problem-eulers-totient-function [a]
  (letfn [(gcd [x y]
            (if (zero? y)
              x
              (recur y (mod x y))))]
    (if (= a 1)
      1
      (count (filter #(= 1 (gcd a %)) (range 1 a))))))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-eulers-totient-function
  (is (= (= (problem-eulers-totient-function 1) 1)))
  (is (= (= (problem-eulers-totient-function 10) (count '(1 3 7 9)) 4)))
  (is (= (= (problem-eulers-totient-function 40) 16)))
  (is (= (= (problem-eulers-totient-function 99) 60))))

(run-tests)
