(defn find_combinations
  "Write a function to find the combinations of sums with vectors in the given vector list."
  [test_list]
  (let [v (vec (or test_list []))
        n (count v)]
    (vec
     (for [i (range n)
           j (range (inc i) n)]
       [(+ (nth v i) (nth v j)) [(nth v i) (nth v j)]]))))