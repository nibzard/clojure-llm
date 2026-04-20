(defn compare_one
  "Create a function that takes integers, floats, or strings representing
  real numbers, and returns the larger variable in its given variable type.
  Return nil if the values are equal.
  Note: If a real number is represented as a string, the floating point might be . or ,
  >>> (compare_one 1 2.5)
  2.5
  >>> (compare_one 1 \"2,3\")
  \"2,3\"
  >>> (compare_one \"5,1\" \"6\")
  \"6\"
  >>> (compare_one \"1\" 1)
  nil"
  [a b]
  (letfn [(to-number [x]
            (cond
              (string? x) (Double/parseDouble (.replace ^String x "," "."))
              (number? x) (double x)
              :else nil))]
    (let [na (to-number a)
          nb (to-number b)]
      (cond
        (= na nb) nil
        (> na nb) a
        :else b))))