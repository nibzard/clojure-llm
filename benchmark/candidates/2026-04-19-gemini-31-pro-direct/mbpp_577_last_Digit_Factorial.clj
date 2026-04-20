(defn last_Digit_Factorial
  "Write a cljthon function to find the last digit in factorial of a given number."
  [n]
  (cond
    (>= n 5) 0
    (= n 4) 4
    (= n 3) 6
    (= n 2) 2
    :else 1))