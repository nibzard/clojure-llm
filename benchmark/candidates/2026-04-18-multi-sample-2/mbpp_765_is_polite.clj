(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (let [impolite? (fn [x] (zero? (bit-and x (dec x))))]
      (loop [x 1
             found 0]
        (if (impolite? x)
          (recur (inc x) found)
          (let [found' (inc found)]
            (if (= found' n)
              x
              (recur (inc x) found'))))))))