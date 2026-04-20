(defn differ-At-One-Bit-Pos
  "Check whether the two numbers differ at one bit position only or not."
  [a b]
  (let [xor (bit-xor a b)]
    (and (not (zero? xor))
         (zero? (bit-and xor (dec xor))))))