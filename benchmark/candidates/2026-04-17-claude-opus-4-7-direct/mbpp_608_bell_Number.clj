(defn bell_Number
  "Write a cljthon function to find nth bell number."
  [n]
  (if (<= n 0)
    1
    (loop [i 1
           bell [1]]
      (if (>= i n)
        (peek bell)
        (let [prev-row (peek bell)
              next-val (reduce + (map #(apply * (drop % (take (inc i) (range))))
                                      (range 0 (inc i))))]
          (recur (inc i) (conj bell next-val)))))))