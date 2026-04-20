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
        apply-op (fn [a op b]
                   (case op
                     "+" (+ a b)
                     "-" (- a b)
                     "*" (* a b)
                     "//" (quot a b)
                     "**" (reduce *' 1 (repeat b a))))
        right-assoc? #{"**"}
        reduce-once (fn [values ops]
                      (let [b (peek values)
                            values' (pop values)
                            a (peek values')
                            values'' (pop values')
                            op (peek ops)
                            ops' (pop ops)]
                        [(conj values'' (apply-op a op b)) ops']))]
    (first
     (first
      (reduce
       (fn [[values ops] [op val]]
         (let [[values ops]
               (loop [values values, ops ops]
                 (if (and (seq ops)
                          (or (> (precedence (peek ops)) (precedence op))
                              (and (= (precedence (peek ops)) (precedence op))
                                   (not (right-assoc? op)))))
                   (let [[values' ops'] (reduce-once values ops)]
                     (recur values' ops'))
                   [values ops]))]
           [(conj values val) (conj ops op)]))
       [(vector (first operand)) []]
       (map vector operator (rest operand))))
    (let [[values ops]
          (reduce
           (fn [[values ops] _]
             (reduce-once values ops))
           [(vector (first operand)) []]
           [])]
      nil))
    (loop [values (first
                   (reduce
                    (fn [[values ops] [op val]]
                      (let [[values ops]
                            (loop [values values, ops ops]
                              (if (and (seq ops)
                                       (or (> (precedence (peek ops)) (precedence op))
                                           (and (= (precedence (peek ops)) (precedence op))
                                                (not (right-assoc? op)))))
                                (let [[values' ops'] (reduce-once values ops)]
                                  (recur values' ops'))
                                [values ops]))]
                        [(conj values val) (conj ops op)]))
                    [(vector (first operand)) []]
                    (map vector operator (rest operand))))
           ops (second
                (reduce
                 (fn [[values ops] [op val]]
                   (let [[values ops]
                         (loop [values values, ops ops]
                           (if (and (seq ops)
                                    (or (> (precedence (peek ops)) (precedence op))
                                        (and (= (precedence (peek ops)) (precedence op))
                                             (not (right-assoc? op)))))
                             (let [[values' ops'] (reduce-once values ops)]
                               (recur values' ops'))
                             [values ops]))]
                     [(conj values val) (conj ops op)]))
                 [(vector (first operand)) []]
                 (map vector operator (rest operand))))]
      (if (seq ops)
        (let [[values' ops'] (reduce-once values ops)]
          (recur values' ops'))
        (peek values)))))