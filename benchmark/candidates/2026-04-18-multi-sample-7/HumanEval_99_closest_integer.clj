(defn closest_integer
  "Create a function that takes a value (string) representing a number
  and returns the closest integer to it. If the number is equidistant
  from two integers, round it away from zero.
  Examples
  >>> (closest_integer \"10\")
  10
  >>> (closest_integer \"15.3\")
  15
  Note:
  Rounding away from zero means that if the given number is equidistant
  from two integers, the one you should return is the one that is the
  farthest from zero. For example closest_integer(\"14.5\") should
  return 15 and closest_integer(\"-14.5\") should return -15."
  [value]
  (let [x (bigdec value)
        sign (compare x 0M)
        abs-x (.abs x)
        floor-abs (.toBigInteger abs-x)
        frac (- abs-x (bigdec floor-abs))
        rounded-abs (if (>= frac 0.5M)
                      (inc floor-abs)
                      floor-abs)
        result (case sign
                 -1 (- rounded-abs)
                 0 0
                 1 rounded-abs)]
    (int result)))