(defn count_Set_Bits
  "Write a cljthon function to count the number of set bits (binary digits with value 1) in a given number."
  [n]
  (loop [x (bigint (or n 0))
         count 0]
    (if (zero? x)
      count
      (recur (bit-and x (dec x)) (inc count)))))