(defn eulerian_num
  "Write a function to find the Eulerian number a(n, m)."
  [n m]
  (cond
    (or (nil? n) (nil? m)) nil
    (or (< n 0) (< m 0)) nil
    (and (zero? n) (zero? m)) 1
    (or (zero? n) (>= m n)) 0
    :else
    (loop [i 1
           row (vec (concat (repeat m 0) [1]))]
      (if (>= i n)
        (last row)
        (recur (inc i)
               (vec (reduce (fn [acc j]
                              (conj acc (* (inc j) (acc (- (count acc) j 1))
                                           (+ (inc (- i j)) (acc (- (count acc) j 1))))))
                            []
                            (range (count row)))))))))