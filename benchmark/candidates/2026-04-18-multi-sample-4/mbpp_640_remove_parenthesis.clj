(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (let [s (or items "")]
    (loop [chars (seq s)
           depth 0
           acc []]
      (if-let [ch (first chars)]
        (cond
          (= ch \() (recur (next chars) (inc depth) acc)
          (= ch \)) (recur (next chars) (max 0 (dec depth)) acc)
          (pos? depth) (recur (next chars) depth acc)
          :else (recur (next chars) depth (conj acc ch)))
        (apply str acc)))))