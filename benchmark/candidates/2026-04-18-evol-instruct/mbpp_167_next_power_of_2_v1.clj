(defn next_power_of_2
  "Return the smallest power of 2 greater than or equal to n.
  Treat nil or non-positive inputs as 1.

  Examples:
  (next_power_of_2 1)   ;=> 1
  (next_power_of_2 5)   ;=> 8
  (next_power_of_2 nil) ;=> 1"
  [n]
  (let [n (if (and n (pos? n)) n 1)]
    (loop [p 1]
      (if (>= p n)
        p
        (recur (* 2 p))))))