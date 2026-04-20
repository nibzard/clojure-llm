(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [idxs (keep-indexed (fn [i c] (when (= c ch) i)) s)
        first-idx (first idxs)
        last-idx (last idxs)]
    (if (nil? first-idx)
      s
      (apply str
             (keep-indexed
              (fn [i c]
                (when (and (not= i first-idx)
                           (not= i last-idx))
                  c))
              s)))))