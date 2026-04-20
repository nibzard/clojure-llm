(defn lazy-amicable-pairs
  "Return a lazy sequence of amicable pairs [a b] with 1 <= a < b <= limit.

  Examples:
  (take 1 (lazy-amicable-pairs 300))
  ;; => ([220 284])

  (into [] (lazy-amicable-pairs 10000))
  ;; => ([220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368])"
  [limit])