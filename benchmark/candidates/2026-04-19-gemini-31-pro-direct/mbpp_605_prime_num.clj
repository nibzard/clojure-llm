(defn prime_num
  "Write a function to check if the given integer is a prime number."
  [num]
  (cond
    (<= num 1) false
    (= num 2) true
    (even? num) false
    :else (not-any? #(zero? (rem num %))
                    (range 3 (inc (int (Math/sqrt num))) 2))))