(defn first_Digit
  "	Write a cljthon function to find the first digit of a given number."
  [n]
  (let [s (str (bigint n))
        s (if (= \- (first s)) (subs s 1) s)]
    (Character/digit ^char (first s) 10)))