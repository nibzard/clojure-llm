(defn left_rotate
  "	Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [mask 0xFFFFFFFF
        n32  (bit-and (long (or n 0)) mask)
        r    (mod (long (or d 0)) 32)]
    (bit-and
      (bit-or
        (bit-and mask (bit-shift-left n32 r))
        (unsigned-bit-shift-right n32 (- 32 r)))
      mask)))