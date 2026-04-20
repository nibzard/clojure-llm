(defn prime_length
  [string]
  (let [n (count string)]
    (and (> n 1)
         (not-any? #(zero? (mod n %))
                   (range 2 (inc (Math/sqrt n)))))))