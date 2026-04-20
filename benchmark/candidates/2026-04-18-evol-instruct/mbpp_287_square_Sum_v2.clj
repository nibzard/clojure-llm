(defn sum-of-odd-squares
  "Return the sum of the squares of the first n odd natural numbers.

  Examples:
  (sum-of-odd-squares 1) ;=> 1
  (sum-of-odd-squares 4) ;=> 84

  If n is zero or negative, return 0."
  [n]
  (if (pos? n)
    (transduce (map #(* % %)) + (take n (iterate #(+ % 2) 1)))
    0))