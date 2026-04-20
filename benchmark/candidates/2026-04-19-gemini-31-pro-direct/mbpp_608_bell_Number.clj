(defn bell_Number [n]
  (let [next-row (fn [prev-row]
                   (reduce (fn [new-row prev-elem]
                             (conj new-row (+ (peek new-row) prev-elem)))
                           [(peek prev-row)]
                           prev-row))]
    (first (nth (iterate next-row [1]) n))))