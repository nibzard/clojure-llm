(defn undulating-prefixes
  "Return a lazy sequence of booleans indicating whether each prefix of `xs` is undulating.

  A sequence is undulating if it alternates between exactly two values, starting from the first
  two elements, and has length at least 2.

  Examples:
  (undulating-prefixes [1])        => (false)
  (undulating-prefixes [1 2])      => (true true)
  (undulating-prefixes [3 4 3 4])   => (false true true true)
  (undulating-prefixes nil)        => ()
  "
  [xs])