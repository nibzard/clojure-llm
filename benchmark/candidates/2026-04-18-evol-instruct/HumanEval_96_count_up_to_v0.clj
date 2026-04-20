(defn count_up_to
  "Return a vector of all prime numbers less than n.

  This variation must treat nil as 0, and it must work correctly for negative
  inputs by returning an empty vector.

  Examples:
  >>> (count_up_to nil)
  []
  >>> (count_up_to -5)
  []
  >>> (count_up_to 10)
  [2 3 5 7]
  >>> (count_up_to 2)
  []
  >>> (count_up_to 20)
  [2 3 5 7 11 13 17 19]"
  [n]
  (let [n (or n 0)
        prime? (fn [x]
                 (and (> x 1)
                      (not-any? #(zero? (mod x %))
                                (range 2 (inc (Math/sqrt x))))))]
    (if (<= n 2)
      []
      (vec (filter prime? (range n))))))