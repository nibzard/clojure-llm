(defn is_samepatterns
  "	Write a function to check whether it follows the sequence given in the patterns vector."
  [colors patterns]
  (let [cs (seq colors)
        ps (seq patterns)]
    (cond
      (nil? ps) true
      (nil? cs) false
      :else
      (let [plen (count patterns)]
        (and (pos? plen)
             (every?
              true?
              (map-indexed
               (fn [i c]
                 (= c (nth patterns (mod i plen))))
               colors))))))