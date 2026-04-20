(defn normalize-punctuation
  "Return a lazy sequence of characters where every space, comma, or period in `chars`
   is replaced by a colon.

   Examples:
   (normalize-punctuation [\\a \\  \\b \\, \\c \\.])
   ;=> (\\a \\: \\b \\: \\c \\:)
   
   Works with any seqable input, including strings and infinite sequences."
  [chars])