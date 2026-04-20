(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (if (zero? n)
      nil
      (apply max
             (loop [i 0
                    dp []]
               (if (= i n)
                 dp
                 (let [x (v i)
                       best-ending-at-i
                       (apply max
                              x
                              (for [j (range i)
                                    :when (< (v j) x)]
                                (max x (* x (dp j)))))]
                   (recur (inc i) (conj dp best-ending-at-i)))))))))