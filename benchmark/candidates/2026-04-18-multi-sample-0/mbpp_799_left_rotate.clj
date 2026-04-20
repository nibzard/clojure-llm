(defn left_rotate
  "	Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [n (bit-and (int (or n 0)) 0xFFFFFFFF)
        d (mod (int (or d 0)) 32)]
    (bit-and
      (bit-or
        (bit-shift-left n d)
        (unsigned-bit-shift-right n (- 32 d)))
      0xFFFFFFFF)))