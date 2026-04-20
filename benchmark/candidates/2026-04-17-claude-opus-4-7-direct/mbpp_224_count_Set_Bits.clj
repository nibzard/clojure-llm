(defn count-set-bits
  "Count the number of set bits (binary digits with value 1) in a given number."
  [n]
  (if (nil? n)
    0
    (loop [cnt 0, x (long n)]
      (if (zero? x)
        cnt
        (recur (inc cnt) (bit-and x (dec x)))))))