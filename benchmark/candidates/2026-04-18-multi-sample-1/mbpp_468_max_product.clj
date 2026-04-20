(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      nil
      (apply max
             (map
              (fn [i]
                (let [x (v i)]
                  (apply max
                         x
                         (for [j (range i)
                               :when (< (v j) x)]
                           (* x (nth % j)))))
              (range n)
              (reductions
               (fn [best i]
                 (assoc best i
                        (let [x (v i)]
                          (apply max
                                 x
                                 (for [j (range i)
                                       :when (< (v j) x)]
                                   (* x (best j)))))))
               {}
               (range n))))))