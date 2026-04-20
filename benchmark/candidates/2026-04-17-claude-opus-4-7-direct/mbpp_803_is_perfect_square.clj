(defn is_perfect_square
  "Write a function to check whether the given number is a perfect square or not."
  [n]
  (and (number? n)
       (>= n 0)
       (let [root (Math/sqrt n)]
         (== root (Math/floor root)))))