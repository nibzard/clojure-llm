(defn xor-char
  "Return a character derived by XOR-ing the character codes in `chars` and mapping the result into A-Z.

  Accepts any sequence of characters, including nil and empty sequences.
  Treat nil as empty.
  Example:
  (xor-char [\\A \\B \\C]) => \\A
  (xor-char nil) => \\A
  (xor-char [\\a \\b]) => a character in the range \\A through \\Z."
  [chars])