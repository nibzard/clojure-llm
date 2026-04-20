(defn last_Digit_Factorial
  "Write a cljthon function to find the last digit in factorial of a given number."
  [n]
  (cond
    (nil? n) nil
    (<= n 1) 1
    (>= n 5) 0
    :else (loop [i 2, result 1]
            (if (> i n)
              result
              (recur (inc i) (mod (* result i) 10))))))