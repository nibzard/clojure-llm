(defn max_product
  "	Write a function to find the maximum product formed by multiplying numbers of an increasing subsequence of that vector."
  [arr]
  (let [n (count arr)]
    (if (zero? n)
      nil
      (let [dp (vec
                (for [i (range n)]
                  (let [ai (nth arr i)
                        best-prev (reduce
                                   (fn [best j]
                                     (if (< (nth arr j) ai)
                                       (max best (nth dp j))
                                       best))
                                   1
                                   (range i))]
                    (max ai (* ai best-prev)))))]
        (apply max dp)))))