(defn find_Index
  "	Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [digits (long (or n 0))]
    (if (<= digits 0)
      nil
      (let [target (if (= digits 1)
                     1N
                     (.pow (biginteger 10) (dec digits)))
            disc (+ 1.0 (* 8.0 (double target)))
            idx (long (Math/ceil (/ (- (Math/sqrt disc) 1.0) 2.0)))]
        idx))))