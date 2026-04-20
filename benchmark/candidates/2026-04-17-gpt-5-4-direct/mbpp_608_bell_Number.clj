(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (long (or n 0))]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (loop [i 1
             row [1]]
        (if (> i n)
          (first row)
          (let [new-row (loop [j 1
                               acc [(last row)]]
                          (if (> j i)
                            acc
                            (recur (inc j)
                                   (conj acc (+ (nth acc (dec j))
                                                (nth row (dec j)))))))]
            (recur (inc i) new-row))))))