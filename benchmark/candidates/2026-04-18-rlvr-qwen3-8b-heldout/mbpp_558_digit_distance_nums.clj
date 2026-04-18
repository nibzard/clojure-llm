(defn digit_distance_nums
  "	Write a cljthon function to find the sum of the per-digit difference between two integers."
  [n1 n2]
  (let [a (Math/abs (long (or n1 0)))
        b (Math/abs (long (or n2 0)))]
    (loop [x a
           y b
           sum 0]
      (if (zero? x)
        sum
        (recur (quot x 10)
               (quot y 10)
               (+ sum (Math/abs (- (mod x 10) (mod y 10)))))))))