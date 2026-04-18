(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (long (or n 0))]
    (if (zero? n)
      1
      (let [rows (range 1 (inc n))]
        (reduce
          (fn [row k]
            (let [prev (nth row (dec k))
                  sum (reduce + (map-indexed (fn (i x) (+ x (nth row i))) (range (dec k))))
                  new (if (= k 1) prev sum)]
              (assoc row k new)))
          [0]
          rows)
        (nth (reduce (fn [row k]
                       (let [prev (nth row (dec k))
                             sum (reduce + (map-indexed (fn (i x) (+ x (nth row i))) (range (dec k))))
                             new (if (= k 1) prev sum)]
                         (assoc row k new)))
                     [0]
                     rows)
              n)))))