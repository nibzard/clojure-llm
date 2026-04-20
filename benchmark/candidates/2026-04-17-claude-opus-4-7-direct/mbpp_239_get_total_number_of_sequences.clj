(defn get_total_number_of_sequences
  "Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (let [limit (long m)
        length (long n)]
    (loop [i 1
           count 0
           dp (vec (repeat (inc limit) 1))]
      (if (> i length)
        count
        (let [new-dp (vec (for [j (range 1 (inc limit))]
                            (let [prev-max (quot j 2)]
                              (if (zero? prev-max)
                                0
                                (reduce + (subvec dp 1 (inc prev-max)))))))
              new-count (reduce + new-dp)]
          (recur (inc i) new-count new-dp))))))