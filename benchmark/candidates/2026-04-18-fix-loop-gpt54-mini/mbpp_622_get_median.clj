(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [a (vec arr1)
        b (vec arr2)
        total (* 2 n)
        mid1 (dec (quot total 2))
        mid2 (quot total 2)]
    (loop [i 0
           j 0
           count 0
           m1 0
           m2 0]
      (cond
        (= count (inc mid2))
        (/ (+ m1 m2) 2.0)

        (>= i n)
        (let [val (b j)]
          (if (= count mid1)
            (recur i (inc j) (inc count) val m2)
            (recur i (inc j) (inc count) m1 val)))

        (>= j n)
        (let [val (a i)]
          (if (= count mid1)
            (recur (inc i) j (inc count) val m2)
            (recur (inc i) j (inc count) m1 val)))

        (<= (a i) (b j))
        (let [val (a i)]
          (if (= count mid1)
            (recur (inc i) j (inc count) val m2)
            (recur (inc i) j (inc count) m1 val)))

        :else
        (let [val (b j)]
          (if (= count mid1)
            (recur i (inc j) (inc count) val m2)
            (recur i (inc j) (inc count) m1 val))))))