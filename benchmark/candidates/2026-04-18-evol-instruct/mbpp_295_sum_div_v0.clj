(defn sum-divisors-of-coll
  "Return the sum of all numeric elements in a collection that evenly divide `n`.
  
  Examples:
  (sum-divisors-of-coll 12 [1 2 3 4 5 6]) ;=> 16
  (sum-divisors-of-coll 7 [1 2 3 4])      ;=> 1
  (sum-divisors-of-coll 0 [0 1 2 3])      ;=> 0"
  [n coll]
  (reduce
    (fn [sum x]
      (if (and (number? x)
               (not (zero? x))
               (zero? (mod n x)))
        (+ sum x)
        sum))
    0
    coll))