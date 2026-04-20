(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (let [s (or items "")]
    (->> s
         (reduce (fn [[depth out] ch]
                   (cond
                     (= ch \() [(inc depth) out]
                     (= ch \)) [(max 0 (dec depth)) out]
                     (pos? depth) [depth out]
                     :else [depth (conj out ch)]))
                 [0 []])
         second
         (apply str)
         clojure.string/trim)))