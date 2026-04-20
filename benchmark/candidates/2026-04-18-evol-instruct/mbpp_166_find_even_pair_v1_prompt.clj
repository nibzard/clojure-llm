(defn count-zero-xor-pairs
  "Return the number of distinct unordered pairs of elements in a collection that xor to zero.

  Works with vectors, lists, or any seqable collection of integers.

  Examples:
  (count-zero-xor-pairs [1 1 2]) ;=> 1
  (count-zero-xor-pairs [0 0 0]) ;=> 3
  (count-zero-xor-pairs []) ;=> 0"
  [xs])