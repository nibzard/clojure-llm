(defn validate
  "	Write a cljthon function takes in an integer and check whether the frequency of each digit in the integer is less than or equal to the digit itself."
  [n]
  (let [digits (map #(- (int %) (int \0)) (str (Math/abs (long n))))
        freqs  (frequencies digits)]
    (every? (fn [[digit freq]] (<= freq digit)) freqs)))