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
  (letfn [(precedence [op]
            (case op
              "**" 3
              "*" 2
              "//" 2
              "+" 1
              "-" 1))
          (int-pow [a b]
            (loop [acc 1N
                   base (bigint a)
                   exp b]
              (if (zero? exp)
                acc
                (recur (* acc base) base (dec exp)))))
          (floor-div [a b]
            (let [q (quot a b)
                  r (rem a b)]
              (if (and (not (zero? r))
                       (not= (neg? a) (neg? b)))
                (dec q)
                q)))
          (apply-op [a op b]
            (case op
              "+" (+ a b)
              "-" (- a b)
              "*" (* a b)
              "//" (floor-div a b)
              "**" (int-pow a b)))
          (reduce-once [vals ops min-prec]
            (loop [vs [(first vals)]
                   os []
                   rem-ops ops
                   rem-vals (rest vals)]
              (if-let [op (first rem-ops)]
                (let [b (first rem-vals)]
                  (if (>= (precedence op) min-prec)
                    (recur (conj (pop vs) (apply-op (peek vs) op b))
                           os
                           (rest rem-ops)
                           (rest rem-vals))
                    (recur (conj vs b)
                           (conj os op)
                           (rest rem-ops)
                           (rest rem-vals))))
                [vs os])))]
    (let [[vals1 ops1] (reduce-once operand operator 3)
          [vals2 ops2] (reduce-once vals1 ops1 2)
          [vals3 _]    (reduce-once vals2 ops2 1)]
      (first vals3))))