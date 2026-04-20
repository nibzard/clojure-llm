(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (let [target (inc n)]
      (loop [x target]
        (if (= 1 (bit-and x 1))
          x
          (recur (bit-shift-right x 1)))))))