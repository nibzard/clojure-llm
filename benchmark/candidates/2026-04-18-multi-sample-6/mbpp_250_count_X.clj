(defn count_X
  "	Write a cljthon function that takes in a vector and an element and counts the occcurences of the element in the list."
  [tup x]
  (count (filter #(= % x) tup)))