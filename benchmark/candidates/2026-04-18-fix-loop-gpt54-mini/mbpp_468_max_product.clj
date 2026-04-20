(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec arr)
        n (count v)]
    (if (zero? n)
      0
      (let [dp (loop [i 0
                      dp []]
                 (if (= i n)
                   dp
                   (let [x (v i)
                         best (reduce (fn [m j]
                                        (let [pj (nth dp j)
                                              y  (v j)]
                                          (if (< y x)
                                            (max m (* pj x))
                                            m)))
                                      x
                                      (range i))]
                     (recur (inc i) (conj dp best)))))]
        (reduce max dp)))))