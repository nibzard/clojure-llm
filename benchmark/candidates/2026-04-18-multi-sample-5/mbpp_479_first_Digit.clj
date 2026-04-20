(defn first_Digit
  "	Write a cljthon function to find the first digit of a given number."
  [n]
  (let [s (str (bigint (if (neg? n) (- n) n)))]
    (Character/digit ^char (first s) 10)))