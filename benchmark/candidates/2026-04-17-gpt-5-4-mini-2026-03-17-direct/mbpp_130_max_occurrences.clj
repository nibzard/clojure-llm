(defn max_occurrences
  "	Write a function to find the item with maximum frequency in a given list."
  [nums]
  (when (seq nums)
    (let [freqs (frequencies nums)
          max-freq (apply max (vals freqs))]
      (first (filter #(= (get freqs %) max-freq) nums)))))