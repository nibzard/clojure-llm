(defn count_up_to
  "Implement a function that takes an non-negative integer and returns a vector of the first n
  integers that are prime numbers and less than n.
  for example:
  >>> (count_up_to 5)
  [2 3]
  >>> (count_up_to 11)
  [2 3 5 7]
  >>> (count_up_to 0)
  []
  >>> (count_up_to 20)
  [2 3 5 7 11 13 17 19]
  >>> (count_up_to 1)
  []
  >>> (count_up_to 18)
  [2 3 5 7 11 13 17]"
  [n]
  (letfn [(prime? [x]
            (and (> x 1)
                 (not-any? #(zero? (mod x %))
                           (range 2 (inc (long (Math/sqrt x)))))))]
    (->> (range 2 n)
         (filter prime?)
         vec)))