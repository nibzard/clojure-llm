(defn intersperse-right
  "Insert a delimiter between every two consecutive elements of a collection.
Works with any seqable input, preserves the original order, and returns a vector.

Examples:
  (intersperse-right [] :x)
  []

  (intersperse-right [1 2 3] :x)
  [1 :x 2 :x 3]

  (intersperse-right '(a b) nil)
  [a nil b]"
  [xs delimiter])