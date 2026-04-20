(defn count_bidirectional
  "Write a function to count bidirectional vector pairs."
  [test_list]
  (loop [lst test_list
         seen {}
         pairs 0]
    (if (empty? lst)
      pairs
      (let [v (first lst)
            rev-v (vec (reverse v))]
        (if (pos? (get seen rev-v 0))
          (recur (rest lst)
                 (update seen rev-v dec)
                 (inc pairs))
          (recur (rest lst)
                 (update seen v (fnil inc 0))
                 pairs))))))