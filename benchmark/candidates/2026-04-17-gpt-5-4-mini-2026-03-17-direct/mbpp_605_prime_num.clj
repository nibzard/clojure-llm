(defn prime_num
  "	Write a function to check if the given integer is a prime number."
  [num]
  (and (integer? num)
       (> num 1)
       (let [limit (long (Math/sqrt num))]
         (not-any? #(zero? (mod num %))
                   (range 2 (inc limit))))))