(defn generate_integers
  "Given two positive integers a and b, return the even digits between a
  and b, in ascending order.
  For example:
  >>> (generate_integers 2 8)
  [2 4 6 8]
  >>> (generate_integers 8 2)
  [2 4 6 8]
  >>> (generate_integers 10 14)
  []"
  [a b]
  (let [start (min a b)
        end (max a b)]
    (vec (filter #(and (even? %) (>= % start) (<= % end)) (range 10)))))