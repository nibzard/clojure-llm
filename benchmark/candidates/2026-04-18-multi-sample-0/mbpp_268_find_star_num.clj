(defn find_star_num
  "	Write a function to find the n'th star number."
  [n]
  (when (and (number? n) (pos? n))
    (let [n (bigint n)]
      (+ (* 6 n (dec n)) 1))))