(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [v1 (vec arr1)
        v2 (vec arr2)
        total (+ n n)]
    (if (or (nil? arr1) (nil? arr2) (zero? n))
      nil
      (loop [i 0
             j 0
             prev nil
             curr nil
             count 0]
        (if (> count n)
          (/ (+ prev curr) 2)
          (cond
            (= i n)
            (recur i (inc j) curr (v2 j) (inc count))

            (= j n)
            (recur (inc i) j curr (v1 i) (inc count))

            (<= (v1 i) (v2 j))
            (recur (inc i) j curr (v1 i) (inc count))

            :else
            (recur i (inc j) curr (v2 j) (inc count)))))))