(defn median
  "Return median of elements in the list l.
  >>> (median [3 1 2 4 5])
  3
  >>> (median [-10 4 6 1000 10 20])
  15.0"
  [l]
  (when-let [xs (seq l)]
    (let [sorted (vec (sort xs))
          n (count sorted)
          mid (quot n 2)]
      (if (odd? n)
        (nth sorted mid)
        (/ (+ (nth sorted (dec mid))
              (nth sorted mid))
           2.0)))))