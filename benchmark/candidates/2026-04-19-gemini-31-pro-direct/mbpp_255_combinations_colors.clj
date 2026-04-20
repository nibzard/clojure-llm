(defn combinations_colors [l n]
  (cond
    (<= n 0) '(())
    (empty? l) '()
    :else (concat (map #(cons (first l) %)
                       (combinations_colors l (dec n)))
                  (combinations_colors (rest l) n))))