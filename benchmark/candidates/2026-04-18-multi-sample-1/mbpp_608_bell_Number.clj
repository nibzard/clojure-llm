(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (or n 0)]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (let [next-row (fn [row]
                       (loop [acc [(last row)]
                              i 1]
                         (if (> i (count row))
                           acc
                           (recur (conj acc (+ (last acc) (nth row (dec i))))
                                  (inc i)))))]
        (first
         (nth
          (iterate next-row [1])
          n)))))