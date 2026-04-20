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
      (let [m1 (if (odd? n)
                 (v1 (quot n 2))
                 (/ (+ (v1 (dec (quot n 2))) (v1 (quot n 2))) 2))
            m2 (if (odd? n)
                 (v2 (quot n 2))
                 (/ (+ (v2 (dec (quot n 2))) (v2 (quot n 2))) 2))]
        (cond
          (= m1 m2) m1
          (< m1 m2)
          (if (odd? n)
            (get_median (subvec v1 (quot n 2) n)
                        (subvec v2 0 (inc (quot n 2)))
                        (inc (quot n 2)))
            (get_median (subvec v1 (dec (quot n 2)) n)
                        (subvec v2 0 (inc (quot n 2)))
                        (inc (quot n 2))))
          :else
          (if (odd? n)
            (get_median (subvec v2 (quot n 2) n)
                        (subvec v1 0 (inc (quot n 2)))
                        (inc (quot n 2)))
            (get_median (subvec v2 (dec (quot n 2)) n)
                        (subvec v1 0 (inc (quot n 2)))
                        (inc (quot n 2))))))))