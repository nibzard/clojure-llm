(defn differ_At_One_Bit_Pos
  "	Write a cljthon function to check whether the two numbers differ at one bit position only or not."
  [a b]
  (let [x (bit-xor (or a 0) (or b 0))]
    (and (not (zero? x))
         (zero? (bit-and x (dec x))))))