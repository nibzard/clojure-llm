(defn median
  "Return median of elements in the list l.
  >>> (median [3 1 2 4 5])
  3
  >>> (median [-10 4 6 1000 10 20])
  15.0"
  [l]
  (when-let [s (seq l)]
    (let [v (vec (sort s))
          n (count v)
          mid (quot n 2)]
      (if (odd? n)
        (nth v mid)
        (/ (+ (nth v (dec mid)) (nth v mid)) 2.0)))))