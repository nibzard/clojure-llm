(defn get_Inv_Count
  "	Write a cljthon function to count inversions in a vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (reduce
     (fn [cnt i]
       (+ cnt
          (count
           (filter #(> (v i) (v %))
                   (range (inc i) n)))))
     0
     (range n))))