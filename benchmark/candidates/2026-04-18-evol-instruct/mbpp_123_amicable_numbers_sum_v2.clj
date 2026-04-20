(defn amicable_pairs-up-to
  "Return a lazy sequence of amicable pairs [a b] with 1 <= a < b <= limit.

  An amicable pair consists of two different positive integers where the sum of the
  proper divisors of each number equals the other number.

  Examples:
  (amicable_pairs-up-to 300)  ;; => ([220 284])
  (amicable_pairs-up-to 10000) ;; => ([220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368])"
  [limit]
  (letfn [(proper-divisor-sum [n]
            (if (= n 1)
              0
              (reduce
               +
               1
               (for [d (range 2 (inc (Math/sqrt n)))
                     :when (zero? (mod n d))]
                 (+ d (if (= d (/ n d)) 0 (/ n d)))))))]
    (for [a (range 2 (inc limit))
          :let [b (proper-divisor-sum a)]
          :when (and (> b a)
                     (<= b limit)
                     (= a (proper-divisor-sum b)))]
      [a b])))