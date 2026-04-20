(defn get_total_number_of_sequences
  "Write a function that takes in positive integers m and n and finds the number of possible sequences of length n, such that each element is a positive integer and is greater than or equal to twice the previous element but less than or equal to m."
  [m n]
  (letfn [(count-seqs [prev remaining]
            (if (zero? remaining)
              1
              (let [start (if (nil? prev) 1 (* 2 prev))]
                (if (> start m)
                  0
                  (reduce + (map #(count-seqs % (dec remaining))
                                 (range start (inc m))))))))]
    (count-seqs nil n)))