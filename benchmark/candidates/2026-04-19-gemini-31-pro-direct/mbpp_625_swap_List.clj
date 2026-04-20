(defn swap_List
  "Write a clojure function to interchange the first and last element in a given list."
  [newList]
  (if-let [n (next newList)]
    (concat [(last newList)] (butlast n) [(first newList)])
    newList))