(defn count_Set_Bits
  "	Write a cljthon function to count the number of set bits (binary digits with value 1) in a given number."
  [n]
  (let [x (long (or n 0))]
    (loop [v (if (neg? x) (bit-not x) x)
           cnt 0]
      (if (zero? v)
        cnt
        (recur (bit-and v (dec v)) (inc cnt))))))