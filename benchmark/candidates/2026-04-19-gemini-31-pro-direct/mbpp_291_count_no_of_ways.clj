(defn count_no_of_ways
  "Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (cond
    (zero? n) 0
    (= 1 n) k
    :else
    (loop [i 2
           same k
           diff (* k (dec k))]
      (if (= i n)
        (+ same diff)
        (recur (inc i)
               diff
               (* (+ same diff) (dec k)))))))