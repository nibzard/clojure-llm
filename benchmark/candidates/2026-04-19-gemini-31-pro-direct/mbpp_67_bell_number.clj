(defn bell_number
  "Write a function to find the number of ways to partition a set of Bell numbers."
  [n]
  (if (zero? n)
    1
    (peek (nth (iterate (fn [prev-row]
                          (reduce (fn [curr-row prev-elem]
                                    (conj curr-row (+ (peek curr-row) prev-elem)))
                                  [(peek prev-row)]
                                  prev-row))
                        [1])
               (dec n)))))