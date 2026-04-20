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
    (if (re-find #"[eE]" s)
      (let [x (Double/parseDouble s)]
        (long (if (neg? x)
                (Math/ceil (- x 0.5))
                (Math/floor (+ x 0.5)))))
      (let [neg? (= \- (first s))
            s' (if (or (= \- (first s)) (= \+ (first s))) (subs s 1) s)
            [int-part frac-part] (clojure.string/split s' #"\." 2)
            int-val (bigint (if (seq int-part) int-part "0"))
            frac-part (or frac-part "")]
        (if (empty? frac-part)
          (if neg? (- int-val) int-val)
          (let [first-digit (Character/digit ^char (first frac-part) 10)
                rest-digits (subs frac-part 1)
                round-up? (or (> first-digit 5)
                              (and (= first-digit 5)
                                   (or (seq rest-digits)
                                       true)))]
            (if neg?
              (- int-val (if round-up? 1 0))
              (+ int-val (if round-up? 1 0)))))))))