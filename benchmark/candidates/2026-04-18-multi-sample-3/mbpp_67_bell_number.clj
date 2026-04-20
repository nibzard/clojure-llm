(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (let [n (if (and (number? n) (not (neg? n))) (long n) 0)]
    (loop [i 0
           row [1]]
      (if (= i n)
        (first row)
        (let [new-row (loop [j 1
                             acc [(last row)]]
                        (if (> j i)
                          acc
                          (recur (inc j)
                                 (conj acc (+ (nth acc (dec j))
                                              (nth row (dec j)))))))]
          (recur (inc i) new-row)))))