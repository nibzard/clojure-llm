(defn prime-partitions
  "Return a lazy sequence of all prime partitions of n.

  A prime partition is a list of primes in nondecreasing order whose sum is n.
  Return an empty sequence for n < 2.

  Examples:
  (prime-partitions 5) => ((2 3) (5))
  (prime-partitions 6) => ((2 2 2) (2 3))

  The result should be lazy and work for larger n without building unnecessary
  intermediate collections."
  [n]
  (letfn [(prime? [x]
            (and (> x 1)
                 (not-any? #(zero? (mod x %))
                           (range 2 (inc (Math/sqrt x))))))
          (parts [target minp]
            (lazy-seq
              (cond
                (zero? target) (list '())
                (< target minp) nil
                :else
                (let [ps (filter prime? (range minp (inc target)))]
                  (mapcat (fn [p]
                            (map #(cons p %)
                                 (parts (- target p) p)))
                          ps)))))]
    (if (< n 2)
      ()
      (parts n 2))))