(defn pair-xor-sum
  "Return the sum of XOR values for all unique unordered pairs in a collection of integers.

  Accepts any sequential collection and ignores nil values.
  Works with negative numbers using Clojure's bit-xor semantics.

  Examples:
  (pair-xor-sum [1 2 3]) ;=> 6
  (pair-xor-sum [1 nil 2]) ;=> 3"
  [xs])