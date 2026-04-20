(defn swap_List
  "	Write a cljthon function to interchange the first and last element in a given list."
  [newList]
  (let [xs (seq newList)]
    (cond
      (nil? xs) newList
      (nil? (next xs)) newList
      :else (let [v (vec xs)
                  last-idx (dec (count v))]
              (seq (assoc v
                     0 (v last-idx)
                     last-idx (v 0)))))))