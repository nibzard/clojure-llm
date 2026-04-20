(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec (or arr1 []))
        b (vec (or arr2 []))
        n (min n (count a) (count b))]
    (cond
      (zero? n) nil
      (= n 1) (/ (+ (nth a 0) (nth b 0)) 2)
      :else
      (letfn [(median-of-sorted [v len]
                (if (odd? len)
                  (nth v (quot len 2))
                  (/ (+ (nth v (dec (quot len 2)))
                        (nth v (quot len 2)))
                     2)))
              (median-rec [a-start b-start len]
                (cond
                  (= len 1)
                  (/ (+ (nth a a-start) (nth b b-start)) 2)

                  (= len 2)
                  (/ (+ (max (nth a a-start) (nth b b-start))
                        (min (nth a (inc a-start)) (nth b (inc b-start))))
                     2)

                  :else
                  (let [m1 (median-of-sorted (subvec a a-start (+ a-start len)) len)
                        m2 (median-of-sorted (subvec b b-start (+ b-start len)) len)]
                    (cond
                      (= m1 m2) m1
                      (< m1 m2)
                      (if (odd? len)
                        (median-rec (+ a-start (quot len 2))
                                    b-start
                                    (inc (quot len 2)))
                        (median-rec (+ a-start (dec (quot len 2)))
                                    b-start
                                    (inc (quot len 2))))

                      :else
                      (if (odd? len)
                        (median-rec a-start
                                    (+ b-start (quot len 2))
                                    (inc (quot len 2)))
                        (median-rec a-start
                                    (+ b-start (dec (quot len 2)))
                                    (inc (quot len 2))))))))]
        (median-rec 0 0 n))))