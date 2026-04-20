(defn bell_number
  "Calculate the nth Bell number using the triangle method."
  [n]
  (if (neg? n)
    0
    (loop [i 1
           row [1]]
      (if (> i n)
        (first row)
        (let [next-row (conj (vec (map + row (cons 0 row)))
                             (first row))]
          (recur (inc i) next-row))))))