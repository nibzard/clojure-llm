(defn is_perfect_square
  "	Write a function to check whether the given number is a perfect square or not. https://www.geeksforgeeks.org/check-if-given-number-is-perfect-square-in-cpp/"
  [n]
  (cond
    (nil? n) false
    (not (integer? n)) false
    (neg? n) false
    :else (let [r (long (Math/sqrt (double n)))]
            (or (= (* r r) n)
                (= (* (inc r) (inc r)) n)))))