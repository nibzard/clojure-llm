(defn find_solution
  "	Write a function that returns integers x and y that satisfy ax + by = n as a vector, or return nil if no solution exists."
  [a b n]
  (letfn [(extended-gcd [a b]
            (if (zero? b)
              [(if (neg? a) (- a) a) (if (neg? a) -1 1) 0]
              (let [[g x1 y1] (extended-gcd b (mod a b))]
                [g
                 y1
                 (- x1 (* y1 (quot a b)))])))]
    (cond
      (and (zero? a) (zero? b))
      (when (zero? n) [0 0])

      :else
      (let [[g x0 y0] (extended-gcd a b)]
        (when (zero? (mod n g))
          (let [k (quot n g)]
            [(* x0 k) (* y0 k)]))))))