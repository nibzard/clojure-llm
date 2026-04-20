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
            frac (or frac-part "0")]
        (if (>= (* 2 (bigint frac)) (bigint (apply str "1" (repeat (count frac) "0"))))
          (if neg?
            (dec (bigint int-part))
            (inc (bigint int-part)))
          (bigint int-part)))
      (bigint s))))