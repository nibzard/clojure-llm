(defn is_perfect_square
  "	Write a function to check whether the given number is a perfect square or not. https://www.geeksforgeeks.org/check-if-given-number-is-perfect-square-in-cpp/"
  [n]
  (and (integer? n)
       (not (neg? n))
       (let [r (long (Math/floor (Math/sqrt (double n))))]
         (= (* r r) n))))