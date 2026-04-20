(defn swap_List
  "	Write a cljthon function to interchange the first and last elements in a list."
  [newList]
  (let [xs (seq newList)]
    (cond
      (nil? xs) newList
      (nil? (next xs)) newList
      :else (concat (list (last xs))
                    (butlast (rest xs))
                    (list (first xs))))))