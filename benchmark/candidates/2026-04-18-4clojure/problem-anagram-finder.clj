(defn problem-anagram-finder [a]
  (->> a
       (group-by #(sort %))
       vals
       (map set)
       (filter #(> (count %) 1))
       set))