(defn lazy-amicable-pairs
  "Return a lazy sequence of amicable pairs [a b] with 1 <= a < b <= limit.

  Examples:
  (take 1 (lazy-amicable-pairs 300))
  ;; => ([220 284])

  (into [] (lazy-amicable-pairs 10000))
  ;; => ([220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368])"
  [limit]
  (letfn [(proper-divisor-sum [n]
            (if (< n 2)
              0
              (reduce + (filter #(zero? (mod n %)) (range 1 n)))))]
    (for [a (range 2 (inc limit))
          :let [b (proper-divisor-sum a)]
          :when (and (> b a)
                     (<= b limit)
                     (= a (proper-divisor-sum b)))]
      [a b])))