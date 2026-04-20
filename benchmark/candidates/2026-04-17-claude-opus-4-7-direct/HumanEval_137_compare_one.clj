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
  (letfn [(parse-value [v]
            (cond
              (number? v) v
              (string? v) (Float/parseFloat (clojure.string/replace v "," "."))))
          (compare [v1 v2]
            (let [n1 (parse-value v1)
                  n2 (parse-value v2)]
              (cond
                (> n1 n2) v1
                (< n1 n2) v2
                :else nil)))]
    (compare a b)))