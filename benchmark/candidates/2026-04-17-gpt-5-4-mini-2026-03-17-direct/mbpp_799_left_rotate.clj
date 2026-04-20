(defn left_rotate
  "	Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [bits 32
        d (mod d bits)
        n (bit-and n 0xffffffff)]
    (bit-and 0xffffffff
             (bit-or (bit-shift-left n d)
                     (unsigned-bit-shift-right n (- bits d))))))