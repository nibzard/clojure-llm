(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        chars (vec s)
        first-idx (first (keep-indexed (fn [i c] (when (= c ch) i)) chars))
        last-idx (last (keep-indexed (fn [i c] (when (= c ch) i)) chars))]
    (if (nil? first-idx)
      s
      (apply str
             (keep-indexed
              (fn [i c]
                (when (and (not= i first-idx)
                           (not= i last-idx))
                  c))
              chars)))))