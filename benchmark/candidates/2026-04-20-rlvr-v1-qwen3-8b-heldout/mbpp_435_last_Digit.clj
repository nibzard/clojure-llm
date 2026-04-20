(defn last_Digit
  "	Write a cljthon function to find the last digit of a given number."
  [n]
  (mod (if (number? n) n 0) 10))