(defn count_no_of_ways
  "	Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (let [mod (long 1e9M)
        n (long (or n 0))
        k (long (or k 0))]
    (if (or (zero? n) (zero? k))
      0
      (let [a0 (mod k)
            a1 (mod (* k (- k 1)))
            a2 (mod (+ a0 a1))]
        (loop [i 3
               a0 a0
               a1 a1
               a2 a2]
          (if (> i n)
            (mod a2)
            (let [next-a2 (mod (+ a0 a1))]
              (recur (inc i) a1 a2 next-a2))))))))