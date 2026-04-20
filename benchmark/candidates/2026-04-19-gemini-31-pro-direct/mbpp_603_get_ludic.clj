(defn get_ludic
  "Write a function to get all ludic numbers smaller than or equal to a given integer."
  [n]
  (if (< n 1)
    []
    (loop [lst (range 2 (inc n))
           res [1]]
      (if (empty? lst)
        res
        (let [x (first lst)
              next-lst (keep-indexed (fn [i v]
                                       (when (not= 0 (mod (inc i) x))
                                         v))
                                     (rest lst))]
          (recur next-lst (conj res x)))))))