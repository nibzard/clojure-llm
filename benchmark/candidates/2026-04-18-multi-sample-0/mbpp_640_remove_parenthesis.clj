(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (let [s (or items "")]
    (->> s
         (reduce (fn [[depth acc] ch]
                   (cond
                     (= ch \() [(inc depth) acc]
                     (= ch \)) [(max 0 (dec depth)) acc]
                     (zero? depth) [depth (conj acc ch)]
                     :else [depth acc]))
                 [0 []])
         second
         (apply str)
         clojure.string/trim)))