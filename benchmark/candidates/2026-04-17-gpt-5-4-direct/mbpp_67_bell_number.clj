(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (let [n (long (or n 0))]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (loop [row [1]
             i 1]
        (if (> i n)
          (first row)
          (let [new-row (loop [r [(last row)]
                               j 1]
                          (if (> j i)
                            r
                            (recur (conj r (+ (nth r (dec j)) (nth row (dec j))))
                                   (inc j))))]
            (recur new-row (inc i))))))))