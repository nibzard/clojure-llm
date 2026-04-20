(defn do_algebra
  "Given two lists operator, and operand. The first list has basic algebra operations, and 
  the second list is a list of integers. Use the two given lists to build the algebric 
  expression and return the evaluation of this expression.
  The basic algebra operations:
  Addition ( + ) 
  Subtraction ( - ) 
  Multiplication ( * ) 
  Floor division ( // ) 
  Exponentiation ( ** ) 
  Example:
  operator['+', '*', '-']
  vector = [2, 3, 4, 5]
  result = 2 + 3 * 4 - 5
  => result = 9
  Note:
      The length of operator list is equal to the length of operand list minus one.
      Operand is a list of of non-negative integers.
      Operator list has at least one operator, and operand list has at least two operands."
  [operator operand]
  (let [precedence {"+" 1, "-" 1, "*" 2, "//" 2, "**" 3}
        apply-op (fn [op a b]
                   (case op
                     "+" (+ a b)
                     "-" (- a b)
                     "*" (* a b)
                     "//" (quot a b)
                     "**" (long (Math/pow a b))))
        reduce-once (fn [ops vals min-prec]
                      (loop [ops ops
                             vals [(first vals)]
                             rem-vals (rest vals)]
                        (if (empty? ops)
                          [ops vals]
                          (let [op (first ops)
                                a (peek vals)
                                b (first rem-vals)]
                            (if (= (precedence op) min-prec)
                              (recur (rest ops)
                                     (conj (pop vals) (apply-op op a b))
                                     (rest rem-vals))
                              (recur (rest ops)
                                     (conj vals b)
                                     (rest rem-vals)))))))]
    (let [[ops1 vals1] (reduce-once operator operand 3)
          [ops2 vals2] (reduce-once ops1 vals1 2)
          [_ vals3]    (reduce-once ops2 vals2 1)]
      (first vals3))))