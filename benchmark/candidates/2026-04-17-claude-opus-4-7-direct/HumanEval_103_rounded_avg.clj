(defn rounded_avg
  "You are given two positive integers n and m, and your task is to compute the
  average of the integers from n through m (including n and m). 
  Round the answer to the nearest integer and convert that to binary.
  If n is greater than m, return -1."
  [n m]
  (if (> n m)
    -1
    (let [cnt (- m n -1)
          avg (/ (+ n m) 2.0)
          rounded (Math/round avg)]
      (str "0b" (Integer/toString rounded 2)))))