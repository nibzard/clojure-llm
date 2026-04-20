(defn get_Inv_Count
  "	Write a cljthon function to count inversions in a vector."
  [arr]
  (let [v (vec (or arr []))
        n (count v)]
    (loop [i 0
           inv-count 0]
      (if (>= i n)
        inv-count
        (recur (inc i)
               (+ inv-count
                  (loop [j (inc i)
                         cnt 0]
                    (if (>= j n)
                      cnt
                      (recur (inc j)
                             (if (> (v i) (v j))
                               (inc cnt)
                               cnt))))))))))