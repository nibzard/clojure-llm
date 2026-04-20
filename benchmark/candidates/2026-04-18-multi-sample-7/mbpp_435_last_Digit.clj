(defn last_Digit
  "	Write a cljthon function to find the last digit of a given number."
  [n]
  (when (number? n)
    (mod (long (Math/abs n)) 10)))