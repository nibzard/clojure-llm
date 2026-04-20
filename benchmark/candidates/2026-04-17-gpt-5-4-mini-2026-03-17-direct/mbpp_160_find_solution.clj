(defn find_solution
  "	Write a function that returns integers x and y that satisfy ax + by = n as a vector, or return nil if no solution exists."
  [a b n]
  (letfn [(egcd [x y]
            (if (zero? y)
              [(Math/abs (long x)) (if (neg? x) -1 1) 0]
              (let [[g s t] (egcd y (mod x y))]
                [g t (- s (* t (quot x y)))])))]
    (cond
      (and (zero? a) (zero? b)) (when (zero? n) [0 0])
      (zero? a) (when (and (not (zero? b)) (zero? (mod n b))) [0 (quot n b)])
      (zero? b) (when (and (not (zero? a)) (zero? (mod n a))) [(quot n a) 0])
      :else
      (let [[g x0 y0] (egcd a b)]
        (when (zero? (mod n g))
          (let [k (quot n g)]
            [(* x0 k) (* y0 k)]))))))