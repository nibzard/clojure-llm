(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (when items
    (apply str
           (keep (fn [ch]
                   (cond
                     (= ch \() nil
                     (= ch \)) nil
                     :else (str ch)))
                 items))))