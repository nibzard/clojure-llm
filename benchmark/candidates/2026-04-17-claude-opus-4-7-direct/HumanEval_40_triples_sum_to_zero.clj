(defn triples_sum_to_zero
  "triples_sum_to_zero takes a list of integers as an input.
  it returns true if there are three distinct elements in the list that
  sum to zero, and false otherwise."
  [l]
  (let [cnt (count l)]
    (cond
      (< cnt 3) false
      :else (let [v (vec l)]
              (loop [i 0]
                (cond
                  (>= i (- cnt 2)) false
                  :else (let [a (v i)]
                          (loop [j (inc i)]
                            (cond
                              (>= j (- cnt 1)) (recur (inc i))
                              :else (let [b (v j)]
                                      (loop [k (inc j)]
                                        (cond
                                          (>= k cnt) (recur (inc j))
                                          (= (+ a b (v k)) 0) true
                                          :else (recur (inc k)))))))))))))