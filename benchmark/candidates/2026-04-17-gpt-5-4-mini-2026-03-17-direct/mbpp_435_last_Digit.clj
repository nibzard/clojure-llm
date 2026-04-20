(defn last_Digit
  "	Write a cljthon function to find the last digit of a given number."
  [n]
  (let [n (bigint (or n 0))]
    (long (mod (if (neg? n) (- n) n) 10))))