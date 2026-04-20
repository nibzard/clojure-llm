(defn left_rotate
  "Write a function to that rotate left bits by d bits a given number. We assume that the number is 32 bit."
  [n d]
  (let [d (mod d 32)
        mask (bit-shift-left 1 d)
        lost-bits (bit-and n (dec mask))
        shifted (bit-shift-left n d)]
    (bit-or shifted (bit-shift-right lost-bits (- 32 d)))))