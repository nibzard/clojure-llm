(defn find_combinations [test_list]
  (let [v (vec test_list)
        n (count v)]
    (for [i (range n)
          j (range (inc i) n)]
      (mapv + (v i) (v j)))))