(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec arr1)
        b (vec arr2)
        total (+ (count a) (count b))]
    (if (or (zero? n) (zero? total))
      nil
      (letfn [(kth [k]
                (loop [i 0 j 0 k k]
                  (cond
                    (>= i (count a)) (nth b (+ j k))
                    (>= j (count b)) (nth a (+ i k))
                    (zero? k) (min (nth a i) (nth b j))
                    :else
                    (let [step (quot (dec k) 2)
                          ni (min (dec (count a)) (+ i step))
                          nj (min (dec (count b)) (+ j step))
                          va (nth a ni)
                          vb (nth b nj)]
                      (if (<= va vb)
                        (recur (inc ni) j (- k (- (inc ni) i)))
                        (recur i (inc nj) (- k (- (inc nj) j))))))))]
        (if (odd? total)
          (double (kth (quot total 2)))
          (/ (+ (kth (dec (quot total 2)))
                (kth (quot total 2)))
             2.0)))))