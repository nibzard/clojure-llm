(defn prime_num
  "	Write a function to check if the given integer is a prime number."
  [num]
  (cond
    (nil? num) false
    (not (integer? num)) false
    (< num 2) false
    (= num 2) true
    (even? num) false
    :else
    (let [limit (long (Math/sqrt num))]
      (not-any? #(zero? (mod num %))
                (range 3 (inc limit) 2)))))