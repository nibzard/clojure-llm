(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (let [n (long (or n 0))]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (loop [i 0
             row [1]]
        (if (= i n)
          (first row)
          (let [next-row (reduce (fn [acc _]
                                   (conj acc (+ (peek acc) (nth row (dec (count acc))))))
                                 [(peek row)]
                                 (range i))]
            (recur (inc i) next-row))))))