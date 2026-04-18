(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        ch (or ch \0)
        idx (first (keep-indexed (fn [i c] (when (= c ch) i)) s))
        last-idx (first (keep-indexed (fn [i c] (when (= c ch) i)) (reverse s)))]
    (cond
      (nil? idx) s
      (nil? last-idx) s
      (= idx last-idx) (subs s 0 (dec (count s)))
      :else (str (subs s 0 idx) (subs s (inc last-idx))))))