(defn odd_length_sum
  "Calculates the sum of all odd length subvectors."
  [arr]
  (if (empty? arr)
    0
    (let [cnt (count arr)]
      (reduce (fn [sum [i v]]
                (+ sum (* v (inc (min i (- cnt i 1))))))
              0
              (map-indexed vector arr)))))