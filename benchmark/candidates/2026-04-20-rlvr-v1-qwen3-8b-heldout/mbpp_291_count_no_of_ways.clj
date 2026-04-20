(defn count_no_of_ways
  "	Write a function to find out the number of ways of painting the fence such that at most 2 adjacent posts have the same color for the given fence with n posts and k colors."
  [n k]
  (let [mod (long 1e9M)
        total (fn [n k]
                (if (zero? n)
                  0
                  (loop [i 2
                         same (mod (* k (dec k)) mod)
                         diff (mod (* k k) mod)]
                    (if (= i n)
                      (+ same diff)
                      (let [new-same (mod (* diff k) mod)
                            new-diff (mod (* (+ same diff) (dec k)) mod)]
                        (recur (inc i) new-same new-diff))))))]
    (total n k)))