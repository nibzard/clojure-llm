(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (int n)]
    (loop [i 0
           row [1]]
      (if (= i n)
        (first row)
        (let [next-row (loop [j 1
                              acc [(last row)]]
                         (if (> j i)
                           acc
                           (recur (inc j) (conj acc (+ (last acc) (nth row (dec j)))))))]
          (recur (inc i) next-row)))))