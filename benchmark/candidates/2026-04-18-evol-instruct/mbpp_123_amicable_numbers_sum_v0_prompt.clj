(defn amicable_pairs
  "Return a lazy sequence of amicable pairs [a b] with 1 <= a < b <= limit.
   Each pair is included once, and the function must work with nil or non-positive limits
   by returning an empty sequence.

   Examples:
   (amicable_pairs 300) => ([220 284])
   (amicable_pairs 10000) => ([220 284] [1184 1210] [2620 2924] [5020 5564] [6232 6368])"
  [limit])