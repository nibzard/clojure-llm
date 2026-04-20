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
  (let [op-fns {'+ +
                '- -
                '* *
                '// quot
                '** (fn [a b] (int (Math/pow a b)))}
        precedence [{'**}
                    {'* '//}
                    {'+ '-}]]
    (loop [ops operator
           vals operand
           [current-precedence & rest-precedence] precedence]
      (if (nil? current-precedence)
        (first vals)
        (let [new-vals (transient [])
              new-ops (transient [])]
          (loop [i 0
                 v vals
                 o ops]
            (if (>= i (count o))
              (do (conj! new-vals (last v))
                  (recur (inc i) (persistent! new-vals) (persistent! new-ops) current-precedence rest-precedence))
              (let [op (nth o i)
                    v1 (nth v i)
                    v2 (nth v (inc i))]
                (if (contains? current-precedence op)
                  (let [result ((op-fns op) v1 v2)]
                    (conj! new-vals result)
                    (recur (inc i) (vec (concat (subvec v (inc (inc i))) [v2])) (vec (rest o))))
                  (do (conj! new-vals v1)
                      (conj! new-ops op)
                      (recur (inc i) v o))))))
          (let [final-vals (persistent! new-vals)
                final-ops (persistent! new-ops)]
            (recur final-ops final-ops rest-precedence)))))))