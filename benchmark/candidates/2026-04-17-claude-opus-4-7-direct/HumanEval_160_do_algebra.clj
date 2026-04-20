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
  (let [;; Map operator symbols to their corresponding Clojure functions.
        ;; For floor division, we use quot which returns the quotient of division.
        ops {"+" +
             "-" -
             "*" *
             "//" quot
             "**" expt}
        ;; Convert the sequence of operators to a sequence of functions.
        op-fns (map ops operator)
        ;; Convert the sequence of operands to a sequence of numbers.
        nums (map #(if (number? %) % (Integer/parseInt %)) operand)]
    ;; Evaluate the expression left-to-right.
    ;; We reduce over the sequence of operator functions, using the first number
    ;; as the initial value. For each subsequent number, we apply the current
    ;; operator function to the accumulated value and the next number.
    (reduce (fn [acc [op num]]
              (op acc num))
            (first nums)
            (map vector op-fns (rest nums)))))