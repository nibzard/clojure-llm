(defn bell_Number
  "	Write a cljthon function to find nth bell number."
  [n]
  (let [n (long n)]
    (cond
      (neg? n) nil
      (zero? n) 1N
      :else
      (let [triangle (atom [[1N]])]
        (dotimes [i n]
          (let [prev (peek @triangle)
                first-val (first prev)
                next-row (vec (cons first-val
                                    (map + prev (rest (conj prev first-val)))))]
            (swap! triangle conj next-row)))
        (peek (peek @triangle))))))