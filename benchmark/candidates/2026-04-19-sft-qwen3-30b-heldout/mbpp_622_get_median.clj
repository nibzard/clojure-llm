(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [merge-sorted (fn [a b]
                       (loop [xs a
                              ys b
                              acc []]
                         (cond
                           (empty? xs) (concat acc ys)
                           (empty? ys) (concat acc xs)
                           :else (let [x (first xs)
                                       y (first ys)]
                                   (if (<= x y)
                                     (recur (rest xs) ys (conj acc x))
                                     (recur xs (rest ys) (conj acc y)))))))
        merged (merge-sorted arr1 arr2)
        mid (quot n 2)]
    (if (even? n)
      (/ (+ (nth merged (dec mid))
            (nth merged mid))
         2.0)
      (nth merged mid))))