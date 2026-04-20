(defn bell_number
  "	Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (cond
    (nil? n) nil
    (neg? n) nil
    (zero? n) 1
    :else
    (letfn [(next-row [row]
              (loop [acc [(peek row)]
                     i 1]
                (if (>= i (count row))
                  acc
                  (recur (conj acc (+ (peek acc) (nth row (dec i))))
                         (inc i)))))]
      (first
       (nth
        (iterate next-row [1])
        n))))