(defn next_power_of_2
  "Return the smallest power of 2 greater than or equal to n.

  Works for positive integers, and returns 1 for n <= 1.

  Examples:
  (next_power_of_2 1)   ;=> 1
  (next_power_of_2 5)   ;=> 8
  (next_power_of_2 16)  ;=> 16"
  [n]
  (let [n (long n)]
    (if (<= n 1)
      1
      (loop [p 1]
        (if (>= p n)
          p
          (recur (bit-shift-left p 1)))))))