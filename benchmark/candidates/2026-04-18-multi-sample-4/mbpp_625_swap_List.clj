(defn swap_List
  "	Write a cljthon function to interchange the first and last element in a given list."
  [newList]
  (let [v (vec newList)
        n (count v)]
    (cond
      (<= n 1) newList
      :else (let [first-elem (v 0)
                  last-elem (v (dec n))
                  swapped (assoc v 0 last-elem (dec n) first-elem)]
              (if (list? newList)
                (apply list swapped)
                swapped)))))