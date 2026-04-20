(defn do_algebra
  [operator operand]
  (let [expr (cons (first operand) (interleave operator (rest operand)))
        process (fn [expr ops]
                  (loop [res []
                         rem expr]
                    (if (empty? rem)
                      res
                      (let [x (first rem)]
                        (if (and (>= (count res) 2)
                                 (contains? ops (peek (pop res))))
                          (let [op-str (peek (pop res))
                                op-fn (ops op-str)
                                a (peek (pop (pop res)))
                                b x
                                val (long (op-fn a b))]
                            (recur (conj (pop (pop (pop res))) val) (rest rem)))
                          (recur (conj res x) (rest rem)))))))]
    (-> expr
        (process {"**" #(Math/pow %1 %2)})
        (process {"*" * "//" #(Math/floorDiv (long %1) (long %2))})
        (process {"+" + "-" -})
        first
        long)))