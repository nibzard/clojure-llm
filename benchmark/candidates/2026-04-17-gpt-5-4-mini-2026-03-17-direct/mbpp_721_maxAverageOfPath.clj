(defn maxAverageOfPath
  [cost]
  (let [n (count cost)]
    (if (zero? n)
      0
      (let [dp (reduce
                (fn [prev row]
                  (vec
                   (map-indexed
                    (fn [j cell]
                      (cond
                        (and (zero? j) (empty? prev))
                        cell

                        (zero? j)
                        (+ cell (nth prev j))

                        :else
                        (+ cell (max (nth prev j) (nth (peek acc) (dec j)))))) ; placeholder
                    row)))
                []
                cost)])))