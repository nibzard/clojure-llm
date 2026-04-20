(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec arr1)
        b (vec arr2)
        total (+ (count a) (count b))]
    (when (pos? total)
      (letfn [(kth [k]
                (loop [i 0 j 0 k k]
                  (cond
                    (>= i (count a)) (b (+ j k))
                    (>= j (count b)) (a (+ i k))
                    (zero? k) (min (a i) (b j))
                    :else
                    (let [half (quot (inc k) 2)
                          new-i (min (count a) (+ i half))
                          new-j (min (count b) (+ j half))
                          ai (a (dec new-i))
                          bj (b (dec new-j))]
                      (if (<= ai bj)
                        (recur new-i j (- k (- new-i i)))
                        (recur i new-j (- k (- new-j j))))))))]
        (if (odd? total)
          (kth (quot total 2))
          (/ (+ (kth (dec (quot total 2)))
                (kth (quot total 2)))
             2.0))))))