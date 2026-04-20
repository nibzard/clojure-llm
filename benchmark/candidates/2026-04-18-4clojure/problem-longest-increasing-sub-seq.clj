(defn problem-longest-increasing-sub-seq [a]
  (let [[best curr]
        (reduce
         (fn [[best curr] x]
           (if (or (empty? curr) (< (peek curr) x))
             [best (conj curr x)]
             [(if (> (count curr) (count best)) curr best) [x]]))
         [[] []]
         a)
        best (if (> (count curr) (count best)) curr best)]
    (if (>= (count best) 2) best [])))