(defn is_polite
  "	Write a function to find nth polite number. geeksforgeeks.org/n-th-polite-number/"
  [n]
  (when (and (integer? n) (pos? n))
    (letfn [(polite? [x]
              (not (zero? (bit-and x (dec x)))))]
      (loop [x 1
             found 0]
        (if (polite? x)
          (let [found' (inc found)]
            (if (= found' n)
              x
              (recur (inc x) found')))
          (recur (inc x) found))))))