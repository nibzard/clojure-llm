(defn find_Index [n]
  (if (<= n 1)
    1
    (long (Math/ceil (/ (dec (Math/sqrt (inc (* 8 (Math/pow 10 (dec n)))))) 2)))))