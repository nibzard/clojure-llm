(defn smallest_change
  [arr]
  (let [n (count arr)]
    (loop [i 0
           j (dec n)
           changes 0]
      (if (>= i j)
        changes
        (recur (inc i)
               (dec j)
               (if (= (nth arr i) (nth arr j))
                 changes
                 (inc changes)))))))