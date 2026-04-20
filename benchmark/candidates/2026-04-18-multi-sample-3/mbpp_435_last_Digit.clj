(defn last_Digit
  "Write a cljthon function to find the last digit of a given number."
  [n]
  (when (some? n)
    (mod (Math/abs (long n)) 10)))