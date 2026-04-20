(defn armstrong_number
  "Return true if `number` is an Armstrong number, false otherwise.

  Unlike the usual integer-only version, this variation accepts a collection of digits
  and should work lazily with any seqable input.

  Examples:
  (armstrong_number [1 5 3])   ;=> true
  (armstrong_number '(1 0 0))   ;=> false
  (armstrong_number (range 3)) ;=> false"
  [digits]
  (let [ds (vec digits)
        n  (count ds)
        sum (reduce + (map #(Math/pow % n) ds))]
    (= sum (reduce + ds))))