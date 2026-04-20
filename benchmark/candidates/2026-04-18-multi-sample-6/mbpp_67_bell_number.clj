(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (zero? n) 1
    :else
    (let [rows (reduce
                 (fn [triangle i]
                   (let [prev-row (peek triangle)
                         first-val (peek prev-row)
                         new-row (reduce
                                   (fn [row j]
                                     (conj row (+ (peek row) (nth prev-row (dec j)))))
                                   [first-val]
                                   (range 1 (inc i)))]
                     (conj triangle new-row)))
                 [[1]]
                 (range 1 (inc n)))]
      (first (peek rows)))))