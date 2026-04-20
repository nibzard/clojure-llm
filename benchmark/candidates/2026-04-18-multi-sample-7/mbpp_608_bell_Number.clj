(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (if (number? n) (long n) -1)]
    (if (neg? n)
      nil
      (loop [i 0
             row [1]]
        (if (= i n)
          (first row)
          (let [next-row (vec
                           (reductions +
                                       (last row)
                                       row))]
            (recur (inc i) next-row)))))))