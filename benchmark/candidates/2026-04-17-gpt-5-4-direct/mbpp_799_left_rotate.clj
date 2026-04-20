(defn left_rotate
  "Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [x (bit-and (long n) 0xFFFFFFFF)
        s (mod (long d) 32)]
    (bit-and
     (bit-or
      (bit-shift-left x s)
      (unsigned-bit-shift-right x (- 32 s)))
     0xFFFFFFFF)))