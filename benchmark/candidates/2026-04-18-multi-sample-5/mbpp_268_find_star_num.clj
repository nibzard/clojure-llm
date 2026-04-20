(defn find_star_num
  "Write a function to find the n'th star number."
  [n]
  (when (some? n)
    (let [n' (bigint n)]
      (/ (+ (* 6 n' (dec n')) 1) 1))))