(defn first_Digit
  "	Write a cljthon function to find the first digit of a given number."
  [n]
  (let [n (Math/abs (long n))]
    (if (< n 10)
      n
      (recur (quot n 10)))))