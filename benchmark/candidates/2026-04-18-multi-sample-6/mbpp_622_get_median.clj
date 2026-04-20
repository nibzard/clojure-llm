(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [v1 (vec arr1)
        v2 (vec arr2)
        total (* 2 n)]
    (cond
      (or (nil? arr1) (nil? arr2) (<= n 0)) nil
      :else
      (loop [i 0
             j 0
             count 0
             m1 0
             m2 0]
        (cond
          (= count (inc n))
          (/ (+ m1 m2) 2.0)

          (= i n)
          (recur i (inc j) (inc count) m2 (v2 j))

          (= j n)
          (recur (inc i) j (inc count) m2 (v1 i))

          (<= (v1 i) (v2 j))
          (recur (inc i) j (inc count) m2 (v1 i))

          :else
          (recur i (inc j) (inc count) m2 (v2 j)))))))