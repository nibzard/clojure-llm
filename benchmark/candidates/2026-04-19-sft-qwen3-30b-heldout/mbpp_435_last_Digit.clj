(defn last_Digit
  "	Write a cljthon function to find the last digit of a given number."
  [n]
  (mod (Math/abs (long (or n 0))) 10))