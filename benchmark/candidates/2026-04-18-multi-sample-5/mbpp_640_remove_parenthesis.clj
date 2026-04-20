(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (when (some? items)
    (->> items
         (reduce (fn [{:keys [depth out]} ch]
                   (cond
                     (= ch \() {:depth (inc depth) :out out}
                     (= ch \)) {:depth (max 0 (dec depth)) :out out}
                     (pos? depth) {:depth depth :out out}
                     :else {:depth depth :out (conj out ch)}))
                 {:depth 0 :out []})
         :out
         (apply str))))