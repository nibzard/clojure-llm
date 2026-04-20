(defn prime_length
  "Write a function that takes a string and returns true if the string
  length is a prime number or false otherwise"
  [string]
  (let [n (count string)]
    (if (< n 2)
      false
      (not-any? #(zero? (rem n %)) 
                (range 2 (inc (int (Math/sqrt n))))))))