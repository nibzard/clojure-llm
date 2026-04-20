(defn max_product
  "Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      nil
      (let [dp (reduce
                 (fn [dp i]
                   (let [x (v i)
                         best (reduce
                                (fn [m j]
                                  (let [y (v j)]
                                    (if (< y x)
                                      (max m (* x (dp j)))
                                      m)))
                                x
                                (range i))]
                     (assoc dp i best)))
                 (vec (repeat n nil))
                 (range n))]
        (reduce max dp)))))