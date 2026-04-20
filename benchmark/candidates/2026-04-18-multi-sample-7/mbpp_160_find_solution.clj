(defn find_solution
  "	Write a function that returns integers x and y that satisfy ax + by = n as a vector, or return nil if no solution exists."
  [a b n]
  (letfn [(egcd [a b]
            (if (zero? b)
              [a 1 0]
              (let [[g x y] (egcd b (mod a b))]
                [g y (- x (* (quot a b) y))])))]
    (cond
      (and (zero? a) (zero? b))
      (when (zero? n) [0 0])

      :else
      (let [[g x0 y0] (egcd a b)
            g (if (neg? g) (- g) g)
            sign (if (neg? (first (egcd a b))) -1 1)]
        (when (zero? (mod n g))
          (let [k (* sign (quot n g))]
            [(* x0 k) (* y0 k)]))))))