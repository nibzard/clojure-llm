(defn prime_num
  "	Write a function to check if the given integer is a prime number."
  [num]
  (let [n (if (integer? num) num nil)]
    (cond
      (nil? n) false
      (< n 2) false
      (= n 2) true
      (even? n) false
      :else (not-any? #(zero? (mod n %))
                      (range 3 (inc (long (Math/sqrt n))) 2)))))