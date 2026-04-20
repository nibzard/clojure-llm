(defn _sum
  "	Write a cljthon function to find the sum of a vector."
  [arr]
  (reduce + 0 (or arr [])))