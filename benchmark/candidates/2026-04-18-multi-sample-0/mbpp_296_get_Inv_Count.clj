(defn get_Inv_Count
  "	Write a cljthon function to count inversions in a vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (reduce
     +
     (for [i (range n)
           j (range (inc i) n)
           :when (> (v i) (v j))]
       1))))