(defn is_prime
  "Return true if a given number is prime, and false otherwise.
  >>> (is_prime 6)
  false
  >>> (is_prime 101)
  true
  >>> (is_prime 11)
  true
  >>> (is_prime 13441)
  true
  >>> (is_prime 61)
  true
  >>> (is_prime 4)
  false
  >>> (is_prime 1)
  false"
  [n]
  (let [n (when (number? n) (long n))]
    (cond
      (nil? n) false
      (< n 2) false
      (= n 2) true
      (even? n) false
      :else
      (let [limit (long (Math/sqrt n))]
        (not-any? #(zero? (mod n %))
                  (range 3 (inc limit) 2))))))