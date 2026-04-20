(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (cond
      (zero? n) nil
      (= n 1) (first v)
      :else
      (let [dp (reduce
                 (fn [dp i]
                   (let [x (v i)
                         best (reduce
                                (fn [best j]
                                  (let [y (v j)
                                        candidate (when (< y x)
                                                    (max x (* x (dp j))))]
                                    (if (and candidate (> candidate best))
                                      candidate
                                      best)))
                                x
                                (range i))]
                     (assoc dp i best)))
                 (vec (repeat n 0))
                 (range n))]
        (reduce max dp))))