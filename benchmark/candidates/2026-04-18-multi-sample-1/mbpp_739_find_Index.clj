(defn find_Index
  "	Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long (or n 0))]
    (if (<= n 0)
      nil
      (let [target (bigint (if (= n 1)
                             1
                             (reduce *' (concat [1N] (repeat (dec n) 10N)))))
            disc (+ 1.0 (* 8.0 (double target)))
            idx (long (Math/ceil (/ (- (Math/sqrt disc) 1.0) 2.0)))]
        idx))))