(defn eulerian-num
  "Return the Eulerian number A(n, m), the number of permutations of 1..n with exactly m ascents.

  The function must accept a sequence of [n m] query pairs and return a lazy sequence of results.

  Examples:
  (eulerian-num [[3 0] [3 1] [4 1]])
  => (1 4 11)

  Invalid queries such as negative n or m, or m >= n, should yield 0."
  [queries])