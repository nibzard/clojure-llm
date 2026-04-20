(defn swap_List
  "Write a clojure function to interchange the first and last elements in a list."
  [newList]
  (if (empty? (rest newList))
    newList
    (concat [(last newList)]
            (drop-last (rest newList))
            [(first newList)])))