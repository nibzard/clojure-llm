(defn find_combinations
  "	Write a function to find the combinations of sums with vectors in the given vector list. https://www.geeksforgeeks.org/cljthon-combinations-of-sum-with-vectors-in-vector-list/"
  [test_list]
  (let [groups (vals (group-by #(reduce + 0 %) (or test_list [])))]
    (vec
     (mapcat (fn [grp]
               (let [v (vec grp)
                     n (count v)]
                 (for [i (range n)
                       j (range (inc i) n)]
                   [(v i) (v j)])))
             groups))))