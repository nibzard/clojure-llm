(defn validate
  "Write a cljthon function takes in an integer and check whether the frequency of each digit in the integer is less than or equal to the digit itself."
  [n]
  (every? (fn [[char-digit freq]]
            (<= freq (Character/digit char-digit 10)))
          (frequencies (filter #(Character/isDigit %) (str n)))))