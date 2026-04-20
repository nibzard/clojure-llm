(defn swap_List
  "	Write a cljthon function to interchange the first and last elements in a list."
  [newList]
  (cond
    (nil? newList) nil
    (<= (count newList) 1) newList
    :else (let [first-el (first newList)
                last-el (last newList)
                middle (butlast (rest newList))]
            (apply list last-el middle first-el))))