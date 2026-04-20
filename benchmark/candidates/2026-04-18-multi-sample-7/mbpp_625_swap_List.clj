(defn swap_List
  "	Write a cljthon function to interchange the first and last element in a given list."
  [newList]
  (let [s (seq newList)]
    (cond
      (nil? s) newList
      (nil? (next s)) newList
      :else (concat [(last s)] (butlast (rest s)) [(first s)]))))