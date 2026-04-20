(defn remove_Occ
  "	Write a cljthon function to remove first and last occurrence of a given character from the string."
  [s ch]
  (let [s (or s "")
        ch (or ch \0)]
    (let [first-idx (.indexOf s (str ch))
          last-idx  (loop [i (dec (count s))]
                      (if (neg? i)
                        -1
                        (if (= (nth s i) ch)
                          i
                          (recur (dec i)))))]
      (cond
        (and (neg? first-idx) (neg? last-idx)) s
        (= first-idx last-idx) (str (subs s 0 first-idx) (subs s (inc first-idx)))
        :else (str (subs s 0 first-idx) (subs s (inc first-idx) last-idx) (subs s (inc last-idx)))))))