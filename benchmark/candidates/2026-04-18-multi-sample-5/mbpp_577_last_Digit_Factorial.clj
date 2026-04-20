(defn last_Digit_Factorial
  "	Write a cljthon function to find the last digit in factorial of a given number."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (< n 2) 1
    (< n 5) (nth [1 1 2 6 4] n)
    :else 0))