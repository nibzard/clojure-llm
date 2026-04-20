(defn remove-occ
  "Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (if (string? s)
    (let [first-idx (.indexOf s (str ch))
          last-idx (.lastIndexOf s (str ch))]
      (cond
        (== -1 first-idx) s
        (== first-idx last-idx) (str (subs s 0 first-idx) (subs s (inc first-idx)))
        :else (str (subs s 0 first-idx) (subs s (inc first-idx) last-idx) (subs s (inc last-idx)))))
    s))