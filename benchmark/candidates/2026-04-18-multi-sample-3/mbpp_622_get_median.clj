(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [v1 (vec (or arr1 []))
        v2 (vec (or arr2 []))
        n  (min n (count v1) (count v2))]
    (cond
      (zero? n) nil
      (= n 1) (/ (+ (v1 0) (v2 0)) 2)
      (= n 2) (/ (+ (max (v1 0) (v2 0))
                    (min (v1 1) (v2 1)))
                 2)
      :else
      (letfn [(median [v len]
                (if (odd? len)
                  (v (quot len 2))
                  (/ (+ (v (dec (quot len 2)))
                        (v (quot len 2)))
                     2)))
              (median-same-size [a b len]
                (cond
                  (zero? len) nil
                  (= len 1) (/ (+ (a 0) (b 0)) 2)
                  (= len 2) (/ (+ (max (a 0) (b 0))
                                  (min (a 1) (b 1)))
                               2)
                  :else
                  (let [m1 (median a len)
                        m2 (median b len)]
                    (if (= m1 m2)
                      m1
                      (if (< m1 m2)
                        (if (odd? len)
                          (recur (subvec a (quot len 2))
                                 (subvec b 0 (inc (quot len 2)))
                                 (inc (quot len 2)))
                          (recur (subvec a (dec (quot len 2)))
                                 (subvec b 0 (inc (quot len 2)))
                                 (inc (quot len 2))))
                        (if (odd? len)
                          (recur (subvec a 0 (inc (quot len 2)))
                                 (subvec b (quot len 2))
                                 (inc (quot len 2)))
                          (recur (subvec a 0 (inc (quot len 2)))
                                 (subvec b (dec (quot len 2)))
                                 (inc (quot len 2)))))))))]
        (median-same-size v1 v2 n))))