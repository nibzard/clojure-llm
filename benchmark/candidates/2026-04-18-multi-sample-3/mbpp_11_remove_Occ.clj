(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        target (if (char? ch) ch (first (str ch)))
        first-idx (.indexOf ^String s (int target))
        last-idx (.lastIndexOf ^String s (int target))]
    (cond
      (or (nil? target) (neg? first-idx)) s
      (= first-idx last-idx)
      (str (subs s 0 first-idx) (subs s (inc first-idx)))
      :else
      (str (subs s 0 first-idx)
           (subs s (inc first-idx) last-idx)
           (subs s (inc last-idx))))))