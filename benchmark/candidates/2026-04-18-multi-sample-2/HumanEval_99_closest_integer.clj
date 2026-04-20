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
  (let [s (clojure.string/trim value)]
    (if (clojure.string/includes? s ".")
      (let [neg? (= \- (first s))
            [int-part frac-part] (clojure.string/split s #"\." 2)
            frac-digit (first frac-part)
            base (Long/parseLong int-part)]
        (if (or (> frac-digit \5)
                (and (= frac-digit \5) (some #(not= % \0) (rest frac-part))))
          (if neg? (dec base) (inc base))
          (if (< frac-digit \5)
            base
            (if neg? (dec base) (inc base)))))
      (Long/parseLong s))))