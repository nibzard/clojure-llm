(defn count_Substrings
  "	Write a cljthon function to count the number of substrings with the sum of digits equal to their length."
  [s]
  (let [digits (mapv #(- (int %) (int \0)) (or s ""))
        prefix (vec (reductions + 0 digits))
        n (count digits)]
    (reduce
     +
     (for [i (range n)
           j (range (inc i) (inc n))
           :let [len (- j i)
                 sum (- (prefix j) (prefix i))]
           :when (= sum len)]
       1))))