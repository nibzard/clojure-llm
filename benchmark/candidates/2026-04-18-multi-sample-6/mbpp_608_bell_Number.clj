(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (long (or n 0))]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (let [next-row (fn [row]
                       (let [start (peek row)]
                         (loop [res [start]
                                i 1]
                           (if (> i (count row))
                             res
                             (recur (conj res (+ (peek res) (nth row (dec i))))
                                    (inc i))))))]
        (first
         (reduce (fn [row _] (next-row row))
                 [1]
                 (range n))))))