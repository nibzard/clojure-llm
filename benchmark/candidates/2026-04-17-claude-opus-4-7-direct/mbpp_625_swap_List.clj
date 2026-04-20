(defn swap_List
  "Write a cljthon function to interchange the first and last element in a given list."
  [newList]
  (if (or (nil? newList) (empty? newList) (= (count newList) 1))
    newList
    (concat [(last newList)] (drop-last (rest newList)) [(first newList)])))