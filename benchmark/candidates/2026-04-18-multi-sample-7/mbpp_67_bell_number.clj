(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (zero? n) 1
    :else
    (let [bell-triangle
          (reduce
            (fn [rows i]
              (let [prev-row (peek rows)
                    row-len  (inc i)
                    first-val (peek prev-row)
                    new-row
                    (reduce
                      (fn [row j]
                        (conj row (+ (nth row (dec j))
                                     (nth prev-row (dec j)))))
                      [first-val]
                      (range 1 row-len))]
                (conj rows new-row)))
            [[1]]
            (range 1 (inc n)))]
      (first (peek bell-triangle)))))