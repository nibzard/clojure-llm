(defn count_Set_Bits
  "	Write a cljthon function to count the number of set bits (binary digits with value 1) in a given number."
  [n]
  (loop [x (long (or n 0))
         cnt 0]
    (if (zero? x)
      cnt
      (recur (bit-and x (dec x)) (inc cnt)))))