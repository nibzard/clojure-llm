(defn differ_At_One_Bit_Pos
  "Write a cljthon function to check whether the two numbers differ at one bit position only or not."
  [a b]
  (and (some? a)
       (some? b)
       (let [x (bit-xor a b)]
         (and (pos? x)
              (zero? (bit-and x (dec x)))))))