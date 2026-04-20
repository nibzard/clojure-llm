(defn median
  "Return median of elements in the list l.
  >>> (median [3 1 2 4 5])
  3
  >>> (median [-10 4 6 1000 10 20])
  15.0"
  [l]
  (let [sorted-l (sort l)
        cnt (count sorted-l)
        mid (quot cnt 2)]
    (if (odd? cnt)
      (nth sorted-l mid)
      (/ (+ (nth sorted-l mid) (nth sorted-l (dec mid))) 2.0))))