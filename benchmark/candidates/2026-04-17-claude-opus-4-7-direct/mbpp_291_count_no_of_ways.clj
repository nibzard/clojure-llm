(defn count_no_of_ways
  "Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (cond
    (or (zero? n) (zero? k)) 0
    (== n 1) k
    (== n 2) (* k k)
    :else
    (let [same k
          diff (* k (dec k))
          total (+ same diff)]
      (loop [i 3
             same same
             diff diff]
        (if (> i n)
          (+ same diff)
          (let [new-diff (* total (dec k))
                new-same diff
                new-total (+ new-same new-diff)]
            (recur (inc i) new-same new-diff)))))))