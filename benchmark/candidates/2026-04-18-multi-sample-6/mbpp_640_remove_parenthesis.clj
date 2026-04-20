(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (when (some? items)
    (let [s (str items)]
      (loop [chars (seq s)
             depth 0
             out []]
        (if-let [ch (first chars)]
          (cond
            (= ch \() (recur (next chars) (inc depth) out)
            (= ch \)) (recur (next chars) (max 0 (dec depth)) out)
            (pos? depth) (recur (next chars) depth out)
            :else (recur (next chars) depth (conj out ch)))
          (apply str out))))))