(defn count-odd-xor-pairs
  "Return the number of pairs of elements in a vector whose bitwise XOR is odd.

  The input may be nil or any sequential collection of integers.
  Only distinct index pairs are counted.

  Examples:
  (count-odd-xor-pairs [1 2 3 4]) ;=> 4
  (count-odd-xor-pairs [2 4 6])   ;=> 0
  (count-odd-xor-pairs nil)       ;=> 0"
  [xs])