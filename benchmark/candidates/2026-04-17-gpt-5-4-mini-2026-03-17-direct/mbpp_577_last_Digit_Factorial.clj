(defn last_Digit_Factorial
  "	Write a cljthon function to find the last digit in factorial of a given number."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (<= n 1) 1
    :else (reduce (fn [acc x] (mod (* acc x) 10))
                  1
                  (range 2 (inc n)))))