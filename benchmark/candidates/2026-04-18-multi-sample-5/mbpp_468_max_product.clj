(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      nil
      (let [dp (reduce
                 (fn [dp i]
                   (let [x (v i)
                         best-prev (reduce
                                     (fn [best j]
                                       (let [y (v j)]
                                         (if (< y x)
                                           (max best
                                                y
                                                (nth dp j)
                                                (* x y)
                                                (* x (nth dp j)))
                                           best)))
                                     Long/MIN_VALUE
                                     (range i))
                         best-i (max x best-prev)]
                     (conj dp best-i)))
                 []
                 (range n))]
        (reduce max dp)))))