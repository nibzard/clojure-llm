(defn validate
  "	Write a cljthon function takes in an integer and check whether the frequency of each digit in the integer is less than or equal to the digit itself."
  [n]
  (let [digits (->> (str (Math/abs (long n)))
                    seq
                    (remove #{\-}))
        freqs  (frequencies digits)]
    (every? (fn [[ch freq]]
              (<= freq (- (int ch) (int \0))))
            freqs)))