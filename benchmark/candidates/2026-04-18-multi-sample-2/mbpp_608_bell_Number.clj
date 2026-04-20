(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (or n 0)]
    (cond
      (neg? n) nil
      (zero? n) 1
      :else
      (let [row (loop [i 1
                       prev [1]]
                  (if (> i n)
                    prev
                    (let [first-val (peek prev)
                          curr (loop [j 1
                                      acc [first-val]]
                                 (if (> j i)
                                   acc
                                   (recur (inc j)
                                          (conj acc (+ (peek acc) (nth prev (dec j)))))))]
                      (recur (inc i) curr))))]
        (first row)))))