(defn xor-bits
  "Return the bitwise XOR of two equal-length bit strings.

  The inputs are strings containing only \\0 and \\1. Return a string of the
  same length, where each character is the XOR of the corresponding input
  bits.

  Examples:
  (xor-bits \"1010\" \"1100\") ;=> \"0110\"
  (xor-bits \"0000\" \"1111\") ;=> \"1111\""
  [bits-a bits-b])