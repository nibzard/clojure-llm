(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        target (if (char? ch) ch (first (str ch)))
        first-idx (.indexOf s (int target))
        last-idx (.lastIndexOf s (int target))]
    (cond
      (or (nil? target) (neg? first-idx)) s
      (= first-idx last-idx)
      (str (subs s 0 first-idx) (subs s (inc first-idx)))
      :else
      (let [without-last (str (subs s 0 last-idx) (subs s (inc last-idx)))]
        (str (subs without-last 0 first-idx)
             (subs without-last (inc first-idx)))))))