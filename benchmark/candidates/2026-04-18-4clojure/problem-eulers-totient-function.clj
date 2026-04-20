(defn problem-eulers-totient-function [a]
  (letfn [(gcd [x y]
            (if (zero? y)
              x
              (recur y (mod x y))))]
    (if (= a 1)
      1
      (count (filter #(= 1 (gcd a %)) (range 1 a))))))