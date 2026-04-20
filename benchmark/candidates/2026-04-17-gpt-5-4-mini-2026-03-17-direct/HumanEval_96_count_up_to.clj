(defn count_up_to
  [n]
  (letfn [(prime? [x]
            (and (> x 1)
                 (not-any? #(zero? (mod x %))
                           (range 2 (inc (Math/sqrt x))))))]
    (vec (filter prime? (range n)))))