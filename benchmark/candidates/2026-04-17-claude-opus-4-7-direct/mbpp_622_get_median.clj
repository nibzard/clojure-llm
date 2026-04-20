(defn get_median
  "Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [cnt (* 2 n)
        low 0
        high n]
    (loop [start low
           end high]
      (let [partition-x (quot (+ start end) 2)
            partition-y (- n partition-x)
            max-left-x (if (zero? partition-x)
                         Integer/MIN_VALUE
                         (nth arr1 (dec partition-x)))
            min-right-x (if (= partition-x n)
                          Integer/MAX_VALUE
                          (nth arr1 partition-x))
            max-left-y (if (zero? partition-y)
                         Integer/MIN_VALUE
                         (nth arr2 (dec partition-y)))
            min-right-y (if (= partition-y n)
                          Integer/MAX_VALUE
                          (nth arr2 partition-y))]
        (if (and (<= max-left-x min-right-y)
                 (<= max-left-y min-right-x))
          (/ (+ (max max-left-x max-left-y)
                (min min-right-x min-right-y))
             2.0)
          (if (> max-left-x min-right-y)
            (recur start (dec partition-x))
            (recur (inc partition-x) end)))))))