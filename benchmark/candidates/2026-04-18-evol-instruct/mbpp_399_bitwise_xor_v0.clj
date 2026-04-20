(defn bitwise_xor
  "Return the bitwise XOR of two integers.

  Examples:
  (bitwise_xor 5 3) => 6
  (bitwise_xor 12 10) => 6

  If either argument is nil, return nil."
  [a b]
  (when (and (some? a) (some? b))
    (bit-xor a b)))