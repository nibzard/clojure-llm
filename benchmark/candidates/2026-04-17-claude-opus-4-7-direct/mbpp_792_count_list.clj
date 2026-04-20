(defn count_list
  "Write a cljthon function to count the number of lists in a given number of lists."
  [input_list]
  (cond
    (nil? input_list) 0
    (list? input_list) (+ 1 (reduce + 0 (map count_list input_list)))
    :else 0))