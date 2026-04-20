(defn sort-by-bitcount
  "Return a vector of the input numbers sorted by the number of 1 bits in their
  two's-complement 64-bit binary representation, in ascending order.

  If two numbers have the same bitcount, sort them by their numeric value.

  The function must:
  - accept any sequential collection of integers
  - return a vector
  - handle nil by returning []
  - work correctly for negative numbers

  Examples:
  >>> (sort-by-bitcount [1 5 2 3 4])
  [1 2 3 4 5]
  >>> (sort-by-bitcount [-2 -3 -4 -5 -6])
  [-6 -5 -4 -3 -2]
  >>> (sort-by-bitcount nil)
  []"
  [xs]
  (->> (or xs [])
       (sort-by (fn [n] [(Long/bitCount (long n)) n]))
       vec))