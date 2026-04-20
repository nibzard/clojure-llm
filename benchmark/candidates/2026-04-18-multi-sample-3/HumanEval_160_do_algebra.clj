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
        shunt-step (fn [[vals ops] [op n]]
                     (let [p (precedence op)
                           should-reduce? (fn [top]
                                            (or (> (precedence top) p)
                                                (and (= (precedence top) p)
                                                     (not (contains? right-assoc? op)))))
                           [vals' ops'] (loop [vals vals, ops ops]
                                          (if (and (seq ops) (should-reduce? (peek ops)))
                                            (recur (first (reduce-once [vals ops]))
                                                   (second (reduce-once [vals ops])))
                                            [vals ops]))]
                       [(conj vals' n) (conj ops' op)]))
        pairs (map vector operator (rest operand))
        [vals ops] (reduce shunt-step [(vector (first operand)) []] pairs)]
    (first
     (first
      (loop [vals vals, ops ops]
        (if (seq ops)
          (recur (first (reduce-once [vals ops]))
                 (second (reduce-once [vals ops])))
          [vals ops]))))))