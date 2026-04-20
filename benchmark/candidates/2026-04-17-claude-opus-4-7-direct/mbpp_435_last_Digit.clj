(defn last-Digit
  "Write a cljthon function to find the last digit of a given number."
  [n]
  (mod (Math/abs n) 10))