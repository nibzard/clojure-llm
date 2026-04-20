(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec (or arr1 []))
        b (vec (or arr2 []))
        k (long (or n 0))]
    (if (zero? k)
      nil
      (let [half (quot k 2)
            [lo hi] (loop [lo 0 hi half]
                      (if (<= hi half)
                        [lo hi]
                        (let [mid (quot (+ lo hi) 2)
                              b-start (- half (inc mid))
                              a-end (inc mid)]
                          (if (and (<= a-end (count a))
                                   (<= b-start (count b))
                                   (<= (nth a (dec a-end)) (nth b b-start)))
                            (recur (inc mid) hi)
                            (recur lo (dec mid))))))]
        (let [a-start (- half (inc lo))
              b-start (- half (inc lo))
              a-end (inc lo)
              b-end (inc lo)
              a-lo (nth a (dec a-start))
              b-lo (nth b (dec b-start))
              a-hi (nth a (dec a-end))
              b-hi (nth b (dec b-end))]
          (if (even? k)
            (/ (+ (min a-hi b-hi) (max a-lo b-lo)) 2.0)
            (min a-hi b-hi)))))))