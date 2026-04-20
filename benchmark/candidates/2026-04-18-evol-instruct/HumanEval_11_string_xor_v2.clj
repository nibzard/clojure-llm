(defn xor-bits
  "Given two collections of bits (0s and 1s), return a vector of XORed bits.
  The inputs may be vectors, lists, or lazy sequences. If the inputs have different
  lengths, treat missing bits as 0.

  Examples:
  (xor-bits [0 1 0] [1 1 0])
  => [1 0 0]

  (xor-bits '(1 0 1 1) [1 1])
  => [0 1 1 1]

  (xor-bits nil [1 0 1])
  => [1 0 1]"
  [a b]
  (vec
   (map (fn [x y] (bit-xor (int (or x 0)) (int (or y 0))))
        (concat (or a []) (repeat 0))
        (concat (or b []) (repeat 0)))))