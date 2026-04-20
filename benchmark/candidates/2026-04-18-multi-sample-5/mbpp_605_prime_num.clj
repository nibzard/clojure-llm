(defn prime_num
  "	Write a function to check if the given integer is a prime number."
  [num]
  (let [n (when (integer? num) num)]
    (cond
      (nil? n) false
      (< n 2) false
      (= n 2) true
      (even? n) false
      :else
      (let [limit (long (Math/sqrt n))]
        (not-any? #(zero? (mod n %)) (range 3 (inc limit) 2))))))