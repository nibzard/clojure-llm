(defn get_median
  "	Write a function to find the median of two sorted lists of same size."
  [arr1 arr2 n]
  (let [v1 (vec arr1)
        v2 (vec arr2)
        total (* 2 n)
        merged (loop [i 0
                      j 0
                      acc []]
                 (cond
                   (= (count acc) total) acc
                   (>= i n) (into acc (subvec v2 j n))
                   (>= j n) (into acc (subvec v1 i n))
                   (<= (v1 i) (v2 j)) (recur (inc i) j (conj acc (v1 i)))
                   :else (recur i (inc j) (conj acc (v2 j)))))]
    (/ (+ (nth merged (dec n)) (nth merged n)) 2.0)))