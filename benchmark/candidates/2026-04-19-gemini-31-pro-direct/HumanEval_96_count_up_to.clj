(defn count_up_to
  "Implement a function that takes an non-negative integer and returns a vector of the first n
  integers that are prime numbers and less than n."
  [n]
  (let [prime? (fn [x]
                 (and (>= x 2)
                      (not-any? #(zero? (rem x %))
                                (range 2 (inc (int (Math/sqrt x)))))))]
    (vec (filter prime? (range 2 n)))))