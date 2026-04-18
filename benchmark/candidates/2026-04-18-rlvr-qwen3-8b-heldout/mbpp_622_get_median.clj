(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec (or arr1 []))
        b (vec (or arr2 []))
        total (or n 0)]
    (if (zero? total)
      nil
      (let [k (quot total 2)]
        (loop [i 0
               j 0
               prev nil
               curr nil]
          (cond
            (and (= i (count a)) (= j (count b)))
            (if (odd? total)
              curr
              (/ (+ curr prev) 2.0))

            (= i (count a))
            (recur i
                   (inc j)
                   curr
                   (nth b j))

            (= j (count b))
            (recur (inc i)
                   j
                   curr
                   (nth a i))

            (and (>= i k) (>= j k))
            (if (odd? total)
              (min (nth a i) (nth b j))
              (/ (+ (min (nth a i) (nth b j))
                    (max (nth a (dec i)) (nth b (dec j))))
                 2.0))

            :else
            (let [ai (nth a i)
                  bj (nth b j)]
              (if (< ai bj)
                (recur (inc i) j ai bj)
                (recur i (inc j) bj ai)))))))))