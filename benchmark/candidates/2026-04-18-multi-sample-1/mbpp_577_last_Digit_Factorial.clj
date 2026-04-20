(defn last_Digit_Factorial
  "	Write a cljthon function to find the last digit in factorial of a given number."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (< n 2) 1
    (< n 5) (reduce * (range 2 (inc n)))
    :else 0))