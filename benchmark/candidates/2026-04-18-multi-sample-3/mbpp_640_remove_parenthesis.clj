(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (let [s (or items "")]
    (loop [chars (seq s)
           depth 0
           result []]
      (if (nil? chars)
        (apply str result)
        (let [ch (first chars)]
          (cond
            (= ch \() (recur (next chars) (inc depth) result)
            (= ch \)) (recur (next chars) (max 0 (dec depth)) result)
            (pos? depth) (recur (next chars) depth result)
            :else (recur (next chars) depth (conj result ch))))))))