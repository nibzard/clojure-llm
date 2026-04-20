(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [v1 (vec (or arr1 []))
        v2 (vec (or arr2 []))
        n  (min n (count v1) (count v2))]
    (cond
      (zero? n) nil
      (= n 1) (/ (+ (v1 0) (v2 0)) 2)
      :else
      (loop [i 0
             j 0
             count 0
             m1 0
             m2 0]
        (cond
          (= count (inc n)) (/ (+ m1 m2) 2)

          (= i n)
          (recur i (inc j) (inc count) m2 (v2 j))

          (= j n)
          (recur (inc i) j (inc count) m2 (v1 i))

          (<= (v1 i) (v2 j))
          (recur (inc i) j (inc count) m2 (v1 i))

          :else
          (recur i (inc j) (inc count) m2 (v2 j))))))