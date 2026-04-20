(defn remove_Occ
  "Write a clojure function to remove first and last occurrence of a given character from the string."
  [s ch]
  (if (empty? s)
    s
    (let [first-idx (clojure.string/index-of s ch)]
      (if (nil? first-idx)
        s
        (let [last-idx (clojure.string/last-index-of s ch)]
          (if (= first-idx last-idx)
            (str (subs s 0 first-idx) (subs s (inc first-idx)))
            (str (subs s 0 first-idx)
                 (subs s (inc first-idx) last-idx)
                 (subs s (inc last-idx)))))))))