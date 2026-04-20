(defn multiple_to_single
  "Write a function to join a list of multiple integers into a single integer."
  [L]
  (if (empty? L)
    0
    (reduce (fn [acc n]
              (if (neg? n)
                (- (* acc 10) n)
                (+ (* acc 10) n)))
            0
            L)))