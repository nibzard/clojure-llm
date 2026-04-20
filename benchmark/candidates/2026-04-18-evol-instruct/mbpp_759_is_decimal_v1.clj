(defn is_decimal?
  "Return true if `x` is a decimal number with exactly 2 digits after the decimal point.

  Accepts numbers or numeric strings. Rejects nil, integers, ratios, and values with
  fewer or more than 2 fractional digits.

  Examples:
  (is_decimal? 12.34)   => true
  (is_decimal? \"12.34\") => true
  (is_decimal? 12)      => false
  (is_decimal? \"12.3\")  => false"
  [x]
  (let [s (cond
            (nil? x) nil
            (string? x) x
            (number? x) (str x)
            :else nil)]
    (boolean
      (when s
        (re-matches #"-?\d+\.\d{2}" s)))))