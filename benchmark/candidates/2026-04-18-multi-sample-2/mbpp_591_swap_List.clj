(defn swap_List
  "	Write a cljthon function to interchange the first and last elements in a list."
  [newList]
  (let [s (seq newList)]
    (cond
      (nil? s) newList
      (nil? (next s)) newList
      :else (concat (list (last s)) (butlast (rest s)) (list (first s))))))