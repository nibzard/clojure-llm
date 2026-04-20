(defn validate
  "Write a cljthon function takes in an integer and check whether the frequency of each digit in the integer is less than or equal to the digit itself."
  [n]
  (let [digits (map #(Character/digit ^char % 10) (str (Math/abs (long n))))
        freqs (frequencies digits)]
    (every? (fn [[d f]] (<= f d)) freqs)))