(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        ch (when (char? ch) (str ch))
        idx (and ch (first (keep-indexed (fn [i c] (when (= c ch) i)) s)))]
    (cond
      (nil? idx) s
      (>= idx (dec (count s))) (subs s 0 (dec (count s)))
      :else (apply str (concat (subs s 0 idx) (subs s (inc idx)))))))