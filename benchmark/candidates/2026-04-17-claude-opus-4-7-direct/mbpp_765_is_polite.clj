(defn is_polite
  "Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (if (<= n 0)
    nil
    (loop [i 1
           polite-count 0]
      (let [power-of-two? (zero? (bit-and i (dec i)))]
        (if power-of-two?
          (recur (inc i) polite-count)
          (if (= polite-count (dec n))
            i
            (recur (inc i) (inc polite-count))))))))