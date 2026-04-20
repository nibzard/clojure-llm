(defn triples_sum_to_zero
  [l]
  (let [v (vec l)
        n (count v)]
    (boolean
      (some zero?
            (for [i (range n)
                  j (range (inc i) n)
                  k (range (inc j) n)]
              (+ (v i) (v j) (v k)))))))