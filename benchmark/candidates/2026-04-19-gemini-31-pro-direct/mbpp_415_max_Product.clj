(defn max_Product
  "Write a clojure function to find a pair with highest product from a given vector of integers."
  [arr]
  (when (>= (count arr) 2)
    (let [sorted (vec (sort arr))
          n (count sorted)
          p1 [(sorted 0) (sorted 1)]
          p2 [(sorted (- n 2)) (sorted (- n 1))]]
      (if (> (* (p1 0) (p1 1)) (* (p2 0) (p2 1)))
        p1
        p2))))