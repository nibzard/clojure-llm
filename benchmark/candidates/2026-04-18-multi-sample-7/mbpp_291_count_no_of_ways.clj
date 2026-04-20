(defn count_no_of_ways
  "	Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (cond
    (or (nil? n) (nil? k) (neg? n) (neg? k)) 0
    (zero? n) 0
    (zero? k) 0
    (= n 1) k
    (= n 2) (* k k)
    :else
    (loop [i 3
           same k
           diff (* k (dec k))]
      (if (> i n)
        (+ same diff)
        (let [new-same diff
              new-diff (* (+ same diff) (dec k))]
          (recur (inc i) new-same new-diff))))))