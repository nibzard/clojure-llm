(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (let [n (long n)]
    (loop [i 0
           row [1]]
      (if (= i n)
        (last row)
        (let [new-row (loop [j 1
                             acc [(last row)]]
                        (if (> j i)
                          acc
                          (recur (inc j) (conj acc (+ (last acc) (nth row (dec j)))))))]
          (recur (inc i) new-row)))))