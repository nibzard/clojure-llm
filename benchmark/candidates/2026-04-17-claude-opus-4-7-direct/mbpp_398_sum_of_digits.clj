(defn sum_of_digits
  "Write a function to compute the sum of digits of each number of a given list."
  [nums]
  (letfn [(sum-digits [n]
            (loop [n n, acc 0]
              (if (zero? n)
                acc
                (recur (quot n 10) (+ acc (rem n 10))))))]
    (map sum-digits nums)))