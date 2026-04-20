(defn find_solution
  "	Write a function that returns integers x and y that satisfy ax + by = n as a vector, or return nil if no solution exists."
  [a b n]
  (letfn [(egcd [a b]
            (if (zero? b)
              [(Math/abs (long a)) (if (neg? a) -1 1) 0]
              (let [[g x y] (egcd b (mod a b))]
                [g y (- x (* y (quot a b)))])))]
    (cond
      (and (zero? a) (zero? b))
      (when (zero? n) [0 0])

      :else
      (let [[g x0 y0] (egcd a b)]
        (when (zero? (mod n g))
          (let [k (/ n g)]
            [(* x0 k) (* y0 k)]))))))