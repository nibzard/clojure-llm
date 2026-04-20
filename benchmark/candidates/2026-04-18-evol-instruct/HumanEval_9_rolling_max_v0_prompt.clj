(defn rolling-max-transducer
  "Given a (possibly infinite) sequence of integers, return a lazy sequence of rolling
  maximums using a transducer-based implementation.

  The first value is always the first input value, and each subsequent output is the
  maximum seen so far.

  Examples:
  >>> (take 5 (rolling-max-transducer [1 2 3 2 3 4 2]))
  (1 2 3 3 3)

  >>> (take 4 (rolling-max-transducer (range)))
  (0 1 2 3)"
  [numbers])