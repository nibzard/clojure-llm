(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (cond
      (zero? n) nil
      (= n 1) (first v)
      :else
      (let [best-ending
            (reduce
             (fn [dp i]
               (let [x (v i)
                     best-i (reduce
                             (fn [best j]
                               (let [y (v j)]
                                 (if (< y x)
                                   (max best
                                        x
                                        (* x (dp j)))
                                   best)))
                             x
                             (range i))]
                 (conj dp best-i)))
             []
             (range n))]
        (apply max best-ending))))