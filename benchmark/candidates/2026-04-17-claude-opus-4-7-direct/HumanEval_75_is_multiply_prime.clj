(defn is_multiply_prime
  "Write a function that returns true if the given number is the multiplication of 3 prime numbers
  and false otherwise.
  Knowing that (a) is less then 100. 
  Example:
  >>> (is_multiply_prime 30)
  true
  30 = 2 * 3 * 5"
  [a]
  (let [primes #{2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97}
        prime-list (sort (vec primes))]
    (loop [p1 prime-list]
      (when-let [n1 (first p1)]
        (if (> n1 a)
          false
          (if (zero? (mod a n1))
            (let [a2 (quot a n1)]
              (loop [p2 (drop-while #(<= % n1) prime-list)]
                (when-let [n2 (first p2)]
                  (if (> n2 a2)
                    false
                    (if (zero? (mod a2 n2))
                      (let [a3 (quot a2 n2)]
                        (contains? primes a3))
                      (recur (rest p2))))))
            (recur (rest p1))))))))