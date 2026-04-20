(defn count_Set_Bits
  "	Write a cljthon function to count the number of set bits (binary digits with value 1) in a given number."
  [n]
  (let [n (long (or n 0))]
    (loop [x n
           count 0]
      (if (zero? x)
        count
        (recur (bit-and x (dec x)) (inc count))))))