(defn swap_List
  "Write a cljthon function to interchange the first and last elements in a list."
  [newList]
  (if (or (nil? newList) (empty? newList) (= 1 (count newList)))
    newList
    (conj (pop (into (empty newList) (next newList)))
          (first newList)
          (last newList))))