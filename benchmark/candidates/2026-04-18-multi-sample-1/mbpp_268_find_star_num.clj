(defn find_star_num
  "	Write a function to find the n'th star number."
  [n]
  (when (and (integer? n) (pos? n))
    (+ 1 (* 6 (quot (* (dec n) n) 2)))))