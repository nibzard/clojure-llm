(defn validate
  "	Write a cljthon function takes in an integer and check whether the frequency of each digit in the integer is less than or equal to the digit itself."
  [n]
  (let [digits (seq (str (Math/abs (long n))))]
    (every?
     (fn [[ch freq]]
       (<= freq (- (int ch) (int \0))))
     (frequencies digits))))