(defn count_Substrings [s]
  (if (empty? s)
    0
    (let [vals (map #(dec (- (int %) 48)) s)
          prefix-sums (reductions + 0 vals)
          freqs (vals (frequencies prefix-sums))]
      (reduce + (map #(quot (* % (dec %)) 2) freqs)))))