(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec arr)
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
                                        pj (dp j)]
                                    (if (< y x)
                                      (max best
                                           x
                                           (* x pj))
                                      best)))
                                x
                                (range i))]
                     (assoc dp i best)))
                 [(v 0)]
                 (range 1 n))]
        (apply max dp)))))