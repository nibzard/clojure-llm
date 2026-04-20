(defn amicable_pairs
  "Return a lazy sequence of amicable pairs [a b] with 1 <= a < b <= limit.
   Each pair is included once, and the function must work with nil or non-positive limits
   by returning an empty sequence.

   Examples:
   (amicable_pairs 300) => ([220 284])
   (amicable_pairs 10000) => ([220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368])"
  [limit]
  (letfn [(sum-proper-divisors [n]
            (if (<= n 1)
              0
              (loop [d 2
                     s 1]
                (if (> (* d d) n)
                  s
                  (if (zero? (mod n d))
                    (let [q (quot n d)]
                      (recur (inc d) (if (= d q) (+ s d) (+ s d q))))
                    (recur (inc d) s))))))]
    (if (and limit (pos? limit))
      (->> (range 2 (inc limit))
           (keep (fn [a]
                   (let [b (sum-proper-divisors a)]
                     (when (and (> b a)
                                (<= b limit)
                                (= a (sum-proper-divisors b)))
                       [a b])))))
      '())))