(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
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
          (let [prev-row row
                first-val (last prev-row)
                new-row (loop [j 1
                               acc [first-val]]
                          (if (> j i)
                            acc
                            (recur (inc j)
                                   (conj acc (+ (nth acc (dec j))
                                                (nth prev-row (dec j)))))))]
            (recur (inc i) new-row))))))