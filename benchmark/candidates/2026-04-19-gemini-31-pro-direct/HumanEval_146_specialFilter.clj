(defn specialFilter [nums]
  (count (filter (fn [n]
                   (and (> n 10)
                        (let [s (str n)
                              odd-char? #{\1 \3 \5 \7 \9}]
                          (and (odd-char? (first s))
                               (odd-char? (last s))))))
                 nums)))