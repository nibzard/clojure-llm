(defn find_star_num
  "Write a function to find the n'th star number."
  [n]
  (let [n (long n)]
    (+ (* n n) (* n (dec n)))))