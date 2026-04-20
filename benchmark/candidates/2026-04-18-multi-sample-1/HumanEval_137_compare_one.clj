(defn compare_one
  "Create a function that takes integers, floats, or strings representing
  real numbers, and returns the larger variable in its given variable type.
  Return nil if the values are equal.
  Note: If a real number is represented as a string, the floating point might be . or ,"
  [a b]
  (letfn [(to-number [x]
            (cond
              (number? x) (double x)
              (string? x) (Double/parseDouble (.replace ^String x \, \.))
              :else nil))]
    (let [na (to-number a)
          nb (to-number b)]
      (cond
        (= na nb) nil
        (> na nb) a
        :else b))))