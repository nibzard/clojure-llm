(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec (concat arr1 []))
        b (vec (concat arr2 []))
        k (quot n 2)]
    (loop [lo 0
           hi (inc (min (count a) (count b)))]
      (if (= lo hi)
        nil
        (let [ia (quot (+ lo hi) 2)
              ib (- k ia)
              a1 (nth a (dec ia) Integer/MAX_VALUE)
              b1 (nth b (dec ib) Integer/MAX_VALUE)
              a2 (nth a ia Integer/MAX_VALUE)
              b2 (nth b ib Integer/MAX_VALUE)]
          (if (<= a1 b2 b1 a2)
            (if (odd? n)
              (min a2 b2)
              (/ (+ (min a2 b2) (max a1 b1)) 2.0))
            (recur (if (< a1 b1) (inc ia) lo)
                   (if (< a1 b1) hi (inc ib)))))))))