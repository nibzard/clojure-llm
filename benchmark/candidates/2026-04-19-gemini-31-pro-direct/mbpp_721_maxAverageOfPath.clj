(defn maxAverageOfPath
  [cost]
  (if (empty? cost)
    0.0
    (let [n (count cost)
          cells (dec (* 2 n))]
      (if (zero? n)
        0.0
        (let [first-row (first cost)
              init-dp (vec (reductions + first-row))
              final-dp (reduce (fn [prev-dp row]
                                 (reduce (fn [curr-dp [prev-val curr-cost]]
                                           (conj curr-dp (+ curr-cost (max (peek curr-dp) prev-val))))
                                         [(+ (first prev-dp) (first row))]
                                         (map vector (rest prev-dp) (rest row))))
                               init-dp
                               (rest cost))]
          (double (/ (peek final-dp) cells)))))))