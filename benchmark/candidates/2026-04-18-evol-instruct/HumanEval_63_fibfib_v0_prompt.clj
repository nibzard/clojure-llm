(defn window-sums
  "Returns a lazy sequence of sums of every sliding window of size `n` over `coll`.

  If `n` is less than or equal to 0, return an empty sequence.
  If `coll` has fewer than `n` items, return an empty sequence.
  The function should work with any seqable collection and should be lazy.

  Examples:
  >>> (window-sums 3 [1 2 3 4 5])
  (6 9 12)
  >>> (window-sums 2 '(10 20 30))
  (30 50)
  >>> (window-sums 4 [1 2])
  ()
  >>> (window-sums 0 [1 2 3])
  ()"
  [n coll])