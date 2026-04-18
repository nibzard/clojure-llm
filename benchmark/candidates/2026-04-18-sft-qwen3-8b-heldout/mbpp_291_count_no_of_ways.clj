(defn count_no_of_ways
  "	Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (let [mod (long 1e9M)]
    (cond
      (nil? n) 0
      (nil? k) 0
      (zero? n) 0
      (zero? k) 0
      (< n 3) k
      :else
      (loop [i 3
             same (mod (* k k))
             diff (mod (* k (- k 1)))]
        (if (> i n)
          (mod (+ same diff))
          (recur (inc i)
                 (mod (* k diff))
                 (mod (+ (* k same) (* k diff)))))))))