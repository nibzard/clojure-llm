(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        target (if (char? ch) ch (first (str ch)))
        first-idx (.indexOf s (int target))
        last-idx (.lastIndexOf s (int target))]
    (cond
      (neg? first-idx) s
      (= first-idx last-idx)
      (str (subs s 0 first-idx) (subs s (inc first-idx)))
      :else
      (str (subs s 0 first-idx)
           (subs s (inc first-idx) last-idx)
           (subs s (inc last-idx))))))