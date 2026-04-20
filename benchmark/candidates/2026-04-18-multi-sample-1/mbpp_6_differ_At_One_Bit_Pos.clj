(defn differ_At_One_Bit_Pos
  "	Write a cljthon function to check whether the two numbers differ at one bit position only or not."
  [a b]
  (let [x (bit-xor (long (or a 0)) (long (or b 0)))]
    (and (pos? x)
         (zero? (bit-and x (dec x))))))