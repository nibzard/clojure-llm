(defn is_perfect_square
  " Write a function to check whether the given number is a perfect square or not. https://www.geeksforgeeks.org/check-if-given-number-is-perfect-square-in-cpp/"
  [n]
  (if (and (number? n) (not (neg? n)))
    (let [r (long (Math/sqrt n))]
      (= (* r r) n))
    false))