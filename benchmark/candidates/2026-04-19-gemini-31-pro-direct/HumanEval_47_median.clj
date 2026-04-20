(defn median
  "Return median of elements in the list l."
  [l]
  (when (seq l)
    (let [sorted (vec (sort l))
          n (count sorted)
          mid (quot n 2)]
      (if (odd? n)
        (sorted mid)
        (/ (+ (sorted (dec mid))
              (sorted mid))
           2.0)))))