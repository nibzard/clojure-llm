(defn get_Inv_Count
  "Write a clojure function to count inversions in a vector."
  [arr]
  (if (empty? arr)
    0
    (let [v (vec arr)
          n (count v)]
      (count (for [i (range n)
                   j (range (inc i) n)
                   :when (> (v i) (v j))]
               1)))))