(defn swap_List
  "	Write a cljthon function to interchange the first and last element in a given list."
  [newList]
  (let [xs (seq newList)]
    (cond
      (nil? xs) newList
      (nil? (next xs)) newList
      :else (cons (last xs) (concat (butlast (rest xs)) [(first xs)])))))