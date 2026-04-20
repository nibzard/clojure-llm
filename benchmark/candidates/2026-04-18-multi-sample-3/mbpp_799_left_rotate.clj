(defn left_rotate
  "	Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [n (long (or n 0))
        d (mod (long (or d 0)) 32)
        mask 0xFFFFFFFF
        x (bit-and n mask)]
    (bit-and
      (bit-or
        (bit-shift-left x d)
        (unsigned-bit-shift-right x (- 32 d)))
      mask)))