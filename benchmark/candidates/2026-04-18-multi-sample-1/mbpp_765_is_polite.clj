(defn is_polite
  "Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (let [impolite? (fn [x] (zero? (bit-and x (dec x))))]
      (loop [i 1
             count-polite 0]
        (if (impolite? i)
          (recur (inc i) count-polite)
          (let [next-count (inc count-polite)]
            (if (= next-count n)
              i
              (recur (inc i) next-count))))))))