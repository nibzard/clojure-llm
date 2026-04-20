(defn differ_At_One_Bit_Pos
  "Write a cljthon function to check whether the two numbers differ at one bit position only or not."
  [a b]
  (let [x (bit-xor a b)]
    (and (not= x 0)
         (zero? (bit-and x (dec x))))))