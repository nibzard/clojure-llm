(defn do_algebra
  "Given two lists operator, and operand. The first list has basic algebra operations, and 
  the second list is a list of integers. Use the two given lists to build the algebric 
  expression and return the evaluation of this expression.
  The basic algebra operations:
  Addition ( + ) 
  Subtraction ( - ) 
  Multiplication ( * ) 
  Floor division ( // ) 
  Exponentiation ( ** )"
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
                                  (not (right-assoc? curr-op)))))
        [vals ops]
        (reduce
         (fn [[vals ops] [op val]]
           (let [[vals ops]
                 (loop [vals vals, ops ops]
                   (if (and (seq ops) (should-reduce? (peek ops) op))
                     (recur (first (reduce-once [vals ops])) (second (reduce-once [vals ops])))
                     [vals ops]))]
             [(conj vals val) (conj ops op)]))
         [(vector (first operand)) []]
         (map vector operator (rest operand)))]
    (first
     (first
      (loop [vals vals, ops ops]
        (if (seq ops)
          (recur (first (reduce-once [vals ops])) (second (reduce-once [vals ops])))
          [vals ops]))))))