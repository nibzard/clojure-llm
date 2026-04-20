(defn truncate-seq
  "Given a sequence of numbers, return a lazy sequence of their fractional parts
  as positive decimals in the range [0, 1).

  Nil values are ignored.
  Works with integers, ratios, and floating-point numbers.

  Examples:
  >>> (take 4 (truncate-seq [3.5 2 7/3 nil -4.25]))
  (0.5 0 1/3 0.75)"
  [numbers]
  (lazy-seq
    (when-let [s (seq numbers)]
      (let [x (first s)]
        (if (nil? x)
          (truncate-seq (rest s))
          (cons (- x (Math/floor (double x)))
                (truncate-seq (rest s))))))))