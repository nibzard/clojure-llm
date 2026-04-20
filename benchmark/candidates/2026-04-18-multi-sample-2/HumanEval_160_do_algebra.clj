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
        reduce-once (fn [vals ops]
                      (let [b (peek vals)
                            vals (pop vals)
                            a (peek vals)
                            vals (pop vals)
                            op (peek ops)
                            ops (pop ops)]
                        [(conj vals (apply-op a op b)) ops]))
        shunt-op (fn [vals ops op]
                   (loop [vals vals, ops ops]
                     (if (and (seq ops)
                              (let [top (peek ops)
                                    p-top (precedence top)
                                    p-cur (precedence op)]
                                (or (> p-top p-cur)
                                    (and (= p-top p-cur)
                                         (not (contains? right-assoc? op))))))
                       (let [[vals' ops'] (reduce-once vals ops)]
                         (recur vals' ops'))
                       [vals (conj ops op)])))]
    (let [[vals ops]
          (reduce (fn [[vals ops] [op n]]
                    (let [[vals' ops'] (shunt-op vals ops op)]
                      [(conj vals' n) ops']))
                  [(vector (first operand)) []]
                  (map vector operator (rest operand)))]
      (first
       (first
        (loop [vals vals, ops ops]
          (if (seq ops)
            (let [[vals' ops'] (reduce-once vals ops)]
              (recur vals' ops'))
            [vals ops])))))))