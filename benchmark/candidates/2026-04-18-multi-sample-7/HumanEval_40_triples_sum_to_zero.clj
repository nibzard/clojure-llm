(defn triples_sum_to_zero
  "triples_sum_to_zero takes a list of integers as an input.
  it returns true if there are three distinct elements in the list that
  sum to zero, and false otherwise.
  >>> (triples_sum_to_zero [1 3 5 0])
  false
  >>> (triples_sum_to_zero [1 3 -2 1])
  true
  >>> (triples_sum_to_zero [1 2 3 7])
  false
  >>> (triples_sum_to_zero [2 4 -5 3 9 7])
  true
  >>> (triples_sum_to_zero [1])
  false"
  [l]
  (let [v (vec l)
        n (count v)]
    (if (< n 3)
      false
      (boolean
       (some true?
             (for [i (range (- n 2))]
               (let [seen (volatile! #{})]
                 (loop [j (inc i)]
                   (cond
                     (>= j n) false
                     (contains? @seen (- (+ (v i) (v j)))) true
                     :else (do
                             (vswap! seen conj (v j))
                             (recur (inc j))))))))))))