(defn left_rotate
  "Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [n32 (bit-and n 0xFFFFFFFF)
        d32 (mod d 32)]
    (bit-or (bit-and (bit-shift-left n32 d32) 0xFFFFFFFF)
            (unsigned-bit-shift-right n32 (- 32 d32)))))