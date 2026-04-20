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
        right-assoc? #{"**"}
        apply-op (fn [a op b]
                   (case op
                     "+" (+ a b)
                     "-" (- a b)
                     "*" (* a b)
                     "//" (quot a b)
                     "**" (long (Math/pow a b))))
        reduce-once (fn [[vals ops]]
                      (let [op (peek ops)
                            b  (peek vals)
                            vals' (pop vals)
                            a  (peek vals')
                            vals'' (pop vals')]
                        [(conj vals'' (apply-op a op b)) (pop ops)]))
        should-reduce? (fn [top-op curr-op]
                         (or (> (precedence top-op) (precedence curr-op))
                             (and (= (precedence top-op) (precedence curr-op))
                                  (not (contains? right-assoc? curr-op)))))
        [vals ops]
        (reduce (fn [[vals ops] [op n]]
                  (let [[vals ops]
                        (loop [vals vals, ops ops]
                          (if (and (seq ops) (should-reduce? (peek ops) op))
                            (let [[vals' ops'] (reduce-once [vals ops])]
                              (recur vals' ops'))
                            [vals ops]))]
                    [(conj vals n) (conj ops op)]))
                [(vector (first operand)) []]
                (map vector operator (rest operand)))]
    (first
     (first
      (iterate (fn [[vals ops]]
                 (if (seq ops)
                   (reduce-once [vals ops])
                   [vals ops]))
               [vals ops])))))