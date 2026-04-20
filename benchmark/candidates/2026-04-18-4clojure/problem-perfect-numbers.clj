(defn problem-perfect-numbers [a]
  (= a
     (reduce +
             (filter #(zero? (mod a %))
                     (range 1 a)))))